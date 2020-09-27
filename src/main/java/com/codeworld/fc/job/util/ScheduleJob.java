package com.codeworld.fc.job.util;

import com.codeworld.fc.common.enums.StatusEnum;
import com.codeworld.fc.job.entity.Job;
import com.codeworld.fc.job.entity.JobLog;
import com.codeworld.fc.job.service.JobLogService;
import com.codeworld.fc.utils.IDGeneratorUtil;
import com.codeworld.fc.utils.SpringContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ClassName ScheduleJob
 * Description 定时任务
 * Author Lenovo
 * Date 2020/9/22
 * Version 1.0
 **/
public class ScheduleJob extends QuartzJobBean {

    private ExecutorService service = Executors.newSingleThreadExecutor();

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleJob.class);

    private JobLogService jobLogService = SpringContextUtil.getBean(JobLogService.class);


    /**
     * 执行任务
     *
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {

        Job job = (Job) jobExecutionContext.getMergedJobDataMap().get(Job.JOB_PARAM_KEY);
        JobLog jobLog = new JobLog();
        jobLog.setJobLogId(IDGeneratorUtil.getNextId());
        jobLog.setJobId(job.getJobId());
        jobLog.setJobBeanName(job.getBeanName());
        jobLog.setJobMethodName(job.getMethodName());
        jobLog.setJobParams(job.getParams());
        jobLog.setJobLogCreateTime(new Date());
        long startTime = System.currentTimeMillis();
        try {
            LOGGER.info("任务准备执行，任务ID：{}", job.getJobId());
            ScheduleRunnable scheduleRunnable = new ScheduleRunnable(job.getBeanName(), job.getMethodName(), job.getParams());
            Future<?> submit = service.submit(scheduleRunnable);
            submit.get();
            long time = System.currentTimeMillis() - startTime;
            jobLog.setJobLogTime(time);
            // 任务状态
            jobLog.setJobStatus(StatusEnum.JOB_SUCCESS);
            LOGGER.info("任务执行完毕，任务ID：{} 总共耗时：{} 毫秒", job.getJobId(), time);
        } catch (Exception e) {
            LOGGER.info("任务执行失败，任务ID：{}, 失败原因：{}", job.getJobId(),StringUtils.substring(e.toString(), 0, 2000));
            long time = System.currentTimeMillis() - startTime;
            jobLog.setJobLogTime(time);
            jobLog.setJobStatus(StatusEnum.JOB_FAIL);
            jobLog.setJobLogError(StringUtils.substring(e.toString(), 0, 2000));
        } finally {
            this.jobLogService.addJobLog(jobLog);
        }
    }
}
