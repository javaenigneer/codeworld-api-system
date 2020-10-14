package com.codeworld.fc.job.service;

import com.codeworld.fc.common.response.DataResponse;
import com.codeworld.fc.common.response.FCResponse;
import com.codeworld.fc.job.entity.JobLog;
import com.codeworld.fc.job.vo.JobLogSearchVO;

import java.util.List;

public interface JobLogService {
    /**
     * 添加任务执行日志
     * @param jobLog
     */
    void addJobLog(JobLog jobLog);

    /**
     * 获取全部任务日志
     * @param jobLogSearchVO
     * @return
     */
    FCResponse<DataResponse<List<JobLog>>> getAllJobLog(JobLogSearchVO jobLogSearchVO);

    /**
     * 删除任务日志
     * @param id
     * @return
     */
    FCResponse<Void> deleteJobLog(Long id);
}
