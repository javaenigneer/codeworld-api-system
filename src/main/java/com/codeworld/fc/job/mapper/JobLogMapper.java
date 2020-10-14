package com.codeworld.fc.job.mapper;

import com.codeworld.fc.job.entity.JobLog;
import com.codeworld.fc.job.vo.JobLogSearchVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface JobLogMapper {
    /**
     * 添加任务执行日志
     * @param jobLog
     */
    void addJobLog(JobLog jobLog);

    /**
     * 获取全部的任务日志
     * @param jobLogSearchVO
     * @return
     */
    List<JobLog> getAllJobLog(JobLogSearchVO jobLogSearchVO);

    /**
     * 手动删除任务日志
     * @param id
     */
    void deleteJobLog(Long id);
}
