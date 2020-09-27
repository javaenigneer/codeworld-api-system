package com.codeworld.fc.job.service;

import com.codeworld.fc.common.response.DataResponse;
import com.codeworld.fc.common.response.FCResponse;
import com.codeworld.fc.job.dto.JobRequestDTO;
import com.codeworld.fc.job.entity.Job;
import com.codeworld.fc.job.vo.JobSearchVO;

import java.util.List;

/**
 * ClassName JobService
 * Description TODO
 * Author Lenovo
 * Date 2020/9/21
 * Version 1.0
**/
public interface JobService {
    /**
     * 获取全部任务
     * @return
     */
    FCResponse<DataResponse<List<Job>>> getAllJob(JobSearchVO jobSearchVO);

    /**
     * 启用或停用任务
     * @param jobId
     * @param status
     * @return
     */
    FCResponse<Void> updateJobStatus(Long jobId, Integer status);

    /**
     * 删除任务
     * @param jobId
     * @return
     */
    FCResponse<Void> deleteJob(Long jobId);

    /**
     * 添加任务
     * @param jobRequestDTO
     * @return
     */
    FCResponse<Void> addJob(JobRequestDTO jobRequestDTO);
}
