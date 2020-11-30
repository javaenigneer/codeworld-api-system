package com.codeworld.fc.job.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.codeworld.fc.common.enums.HttpFcStatus;
import com.codeworld.fc.common.enums.HttpMsg;
import com.codeworld.fc.common.exception.FCException;
import com.codeworld.fc.common.response.DataResponse;
import com.codeworld.fc.common.response.FCResponse;
import com.codeworld.fc.job.dto.JobRequestDTO;
import com.codeworld.fc.job.entity.Job;
import com.codeworld.fc.job.mapper.JobMapper;
import com.codeworld.fc.job.service.JobService;
import com.codeworld.fc.job.util.ScheduleUtils;
import com.codeworld.fc.job.vo.JobSearchVO;
import com.codeworld.fc.utils.IDGeneratorUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

/**
 * ClassName JobServiceImpl
 * Description TODO
 * Author Lenovo
 * Date 2020/9/21
 * Version 1.0
 **/
@Service
public class JobServiceImpl implements JobService {

    @Autowired(required = false)
    private JobMapper jobMapper;

    @Autowired(required = false)
    private Scheduler scheduler;

    @PostConstruct
    public void init() {

        // 获取全部的任务调度
        JobSearchVO jobSearchVO = new JobSearchVO();
        List<Job> jobs = this.jobMapper.getAllJob(jobSearchVO);
        // 如果不存在则创建
        jobs.forEach(job -> {

            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, job.getJobId());
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, job);
            } else {
                ScheduleUtils.updateScheduleJob(scheduler, job);
            }
        });
    }

    /**
     * 获取全部任务
     *
     * @return
     */
    @Override
    public FCResponse<DataResponse<List<Job>>> getAllJob(JobSearchVO jobSearchVO) {

        PageHelper.startPage(jobSearchVO.getPage(), jobSearchVO.getLimit());

        try {
            List<Job> jobs = this.jobMapper.getAllJob(jobSearchVO);

            if (CollectionUtils.isEmpty(jobs)) {
                return FCResponse.dataResponse(HttpFcStatus.DATASUCCESSGET.getCode(), HttpMsg.job.JOB_DATA_EMPTY.getMsg(), DataResponse.dataResponse(jobs, 0L));
            }
            PageInfo<Job> pageInfo = new PageInfo<>(jobs);
            return FCResponse.dataResponse(HttpFcStatus.DATASUCCESSGET.getCode(), HttpMsg.job.JOB_DATA_SUCCESS.getMsg(), DataResponse.dataResponse(pageInfo.getList(), pageInfo.getTotal()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new FCException("系统错误");
        }
    }

    /**
     * 启用或停用任务
     *
     * @param jobId
     * @param status
     * @return
     */
    @Override
    public FCResponse<Void> updateJobStatus(Long jobId, Integer status) {

        if (jobId == null || jobId <= 0) {
            return FCResponse.dataResponse(HttpFcStatus.PARAMSERROR.getCode(), HttpMsg.job.JOB_PARAMS_ERROR.getMsg());

        }
        try {

            // 启用任务
            if (StringUtils.equals(Job.ScheduleStatus.NORMAL.getValue(),String.valueOf(status))){
                // 修改任务状态
                Job job = new Job();
                job.setJobId(jobId);
                job.setStatus(String.valueOf(status));
                this.jobMapper.updateJobStatus(job);
                // 根据id获取任务
                job = this.jobMapper.getJobById(job.getJobId());
                // 恢复任务
                ScheduleUtils.resumeJob(scheduler,job.getJobId());

            }
            // 停用任务
            if (StringUtils.equals(Job.ScheduleStatus.PAUSE.getValue(),String.valueOf(status))){
                // 修改任务状态
                Job job = new Job();
                job.setJobId(jobId);
                job.setStatus(String.valueOf(status));
                this.jobMapper.updateJobStatus(job);
                // 根据id获取任务
                job = this.jobMapper.getJobById(job.getJobId());
                // 暂停任务
                ScheduleUtils.pauseJob(scheduler,job.getJobId());
            }
            return FCResponse.dataResponse(HttpFcStatus.DATASUCCESSGET.getCode(),HttpMsg.job.JOB_STATUS_SUCCESS.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            throw new FCException("系统错误");
        }
    }

    /**
     * 删除任务
     *
     * @param jobId
     * @return
     */
    @Override
    public FCResponse<Void> deleteJob(Long jobId) {

        if (jobId == null || jobId <= 0){
            return FCResponse.dataResponse(HttpFcStatus.PARAMSERROR.getCode(),HttpMsg.job.JOB_PARAMS_ERROR.getMsg());
        }

        try {
            ScheduleUtils.deleteScheduleJob(scheduler,jobId);
            // 删除任务
            this.jobMapper.deleteJobById(jobId);
            return FCResponse.dataResponse(HttpFcStatus.DATASUCCESSGET.getCode(),HttpMsg.job.JOB_DELETE_SUCCESS.getMsg());
        }catch (Exception e){
            e.printStackTrace();
            throw new FCException("系统错误");
        }
    }

    /**
     * 添加任务
     *
     * @param jobRequestDTO
     * @return
     */
    @Override
    public FCResponse<Void> addJob(JobRequestDTO jobRequestDTO) {
        try {
            Job job = new Job();
            BeanUtil.copyProperties(jobRequestDTO,job);
            job.setJobId(IDGeneratorUtil.getNextId());
            job.setCreateTime(new Date());
            job.setUpdateTime(job.getCreateTime());
            this.jobMapper.addJob(job);
            // 创建定时任务
            ScheduleUtils.createScheduleJob(scheduler,job);
            return FCResponse.dataResponse(HttpFcStatus.DATASUCCESSGET.getCode(),HttpMsg.job.JOB_ADD_SUCCESS.getMsg());
        }catch (Exception e){
            e.printStackTrace();
            throw new FCException("系统错误");
        }

    }
}
