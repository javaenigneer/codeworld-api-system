package com.codeworld.fc.job.mapper;

import com.codeworld.fc.job.entity.Job;
import com.codeworld.fc.job.vo.JobSearchVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface JobMapper {
    /**
     * 获取所有任务
     * @param jobSearchVO
     * @return
     */
    List<Job> getAllJob(JobSearchVO jobSearchVO);

    /**
     * 更新任务状态
     * @param job
     */
    void updateJobStatus(Job job);

    /**
     * 根据Id获取任务
     * @param jobId
     * @return
     */
    Job getJobById(Long jobId);

    /**
     * 删除任务
     * @param jobId
     */
    void deleteJobById(Long jobId);

    /**
     * 添加任务
     * @param job
     */
    void addJob(Job job);
}
