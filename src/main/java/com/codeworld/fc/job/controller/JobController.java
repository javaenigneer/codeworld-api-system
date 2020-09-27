package com.codeworld.fc.job.controller;

import com.codeworld.fc.common.annotation.ControllerEndpoint;
import com.codeworld.fc.common.response.DataResponse;
import com.codeworld.fc.common.response.FCResponse;
import com.codeworld.fc.job.dto.JobRequestDTO;
import com.codeworld.fc.job.entity.Job;
import com.codeworld.fc.job.service.JobService;
import com.codeworld.fc.job.vo.JobSearchVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * ClassName JobController
 * Description 任务调度管理接口
 * Author Lenovo
 * Date 2020/9/21
 * Version 1.0
**/
@RestController
@RequestMapping("system-job")
@Api(tags = "任务调度管理接口")
public class JobController {

    @Autowired(required = false)
    private JobService jobService;

    @PostMapping("get-all-job")
    @ApiOperation("获取全部任务")
    @PreAuthorize("hasAuthority('job')")
    @ControllerEndpoint(operation = "获取全部任务",exceptionMessage = "获取全部任务失败")
    public FCResponse<DataResponse<List<Job>>> getAllJob(@RequestBody JobSearchVO jobSearchVO){
        return this.jobService.getAllJob(jobSearchVO);
    }

    @PostMapping("update-job-status")
    @ApiOperation("启用或停用任务")
    @ControllerEndpoint(operation = "启用或停用任务",exceptionMessage = "启用或停用任务失败")
    public FCResponse<Void> updateJobStatus(@RequestParam("jobId") Long jobId,
                                            @RequestParam("status") Integer status){
        return this.jobService.updateJobStatus(jobId,status);
    }

    @PostMapping("delete-job")
    @ApiOperation("删除任务")
    @PreAuthorize("hasAuthority('job:delete')")
    @ControllerEndpoint(operation = "删除任务",exceptionMessage = "删除任务失败")
    public FCResponse<Void> deleteJob(@RequestParam("jobId") Long jobId){
        return this.jobService.deleteJob(jobId);
    }

    @PostMapping("add-job")
    @ApiOperation("添加任务")
    @PreAuthorize("hasAuthority('job:add')")
    @ControllerEndpoint(operation = "添加任务",exceptionMessage = "添加任务失败")
    public FCResponse<Void> addJob(@RequestBody @Valid JobRequestDTO jobRequestDTO){
        return this.jobService.addJob(jobRequestDTO);
    }
}
