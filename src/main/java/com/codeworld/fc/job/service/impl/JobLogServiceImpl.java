package com.codeworld.fc.job.service.impl;

import com.codeworld.fc.common.enums.HttpFcStatus;
import com.codeworld.fc.common.enums.HttpMsg;
import com.codeworld.fc.common.exception.FCException;
import com.codeworld.fc.common.response.DataResponse;
import com.codeworld.fc.common.response.FCResponse;
import com.codeworld.fc.job.entity.JobLog;
import com.codeworld.fc.job.mapper.JobLogMapper;
import com.codeworld.fc.job.service.JobLogService;
import com.codeworld.fc.job.vo.JobLogSearchVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * ClassName JobLogServiceImpl
 * Description
 * Author Lenovo
 * Date 2020/9/25
 * Version 1.0
 **/
@Service
public class JobLogServiceImpl implements JobLogService {

    @Autowired(required = false)
    private JobLogMapper jobLogMapper;

    /**
     * 添加任务执行日志
     *
     * @param jobLog
     */
    @Override
    public void addJobLog(JobLog jobLog) {

        this.jobLogMapper.addJobLog(jobLog);
    }

    /**
     * 获取全部任务日志
     *
     * @param jobLogSearchVO
     * @return
     */
    @Override
    public FCResponse<DataResponse<List<JobLog>>> getAllJobLog(JobLogSearchVO jobLogSearchVO) {
        PageHelper.startPage(jobLogSearchVO.getPage(), jobLogSearchVO.getLimit());
        try {
            List<JobLog> jobLogs = this.jobLogMapper.getAllJobLog(jobLogSearchVO);
            if (CollectionUtils.isEmpty(jobLogs)) {
                return FCResponse.dataResponse(HttpFcStatus.DATASUCCESSGET.getCode(), HttpMsg.jobLog.JOB_LOG_DATA_EMPTY.getMsg(), DataResponse.dataResponse(jobLogs, 0L));
            }
            PageInfo<JobLog> pageInfo = new PageInfo<>(jobLogs);
            return FCResponse.dataResponse(HttpFcStatus.DATASUCCESSGET.getCode(), HttpMsg.jobLog.JOB_LOG_DATA_SUCCESS.getMsg(), DataResponse.dataResponse(pageInfo.getList(), pageInfo.getTotal()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new FCException("系统错误");
        }
    }
}
