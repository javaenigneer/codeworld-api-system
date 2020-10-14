package com.codeworld.fc.job.controller;

import com.codeworld.fc.common.annotation.ControllerEndpoint;
import com.codeworld.fc.common.response.DataResponse;
import com.codeworld.fc.common.response.FCResponse;
import com.codeworld.fc.job.entity.JobLog;
import com.codeworld.fc.job.service.JobLogService;
import com.codeworld.fc.job.vo.JobLogSearchVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName JobLogController
 * Description 任务日志接口管理
 * Author Lenovo
 * Date 2020/9/25
 * Version 1.0
 **/
@RestController
@RequestMapping("system-job-log")
@Api(tags = "任务日志接口管理")
public class JobLogController {

    @Autowired(required = false)
    private JobLogService jobLogService;

    @PostMapping("get-all-job-log")
    @ApiOperation("获取全部任务日志")
    @ControllerEndpoint(operation = "获取全部任务日志", exceptionMessage = "获取全部任务日志失败")
    public FCResponse<DataResponse<List<JobLog>>> getAllJobLog(@RequestBody JobLogSearchVO jobLogSearchVO) {
        return this.jobLogService.getAllJobLog(jobLogSearchVO);
    }

    @PostMapping("delete-job-log")
    @ApiOperation("删除任务日志")
    @ControllerEndpoint(operation = "删除任务日志",exceptionMessage = "删除任务日志失败")
    public FCResponse<Void> deleteJobLog(@RequestParam("id") Long id){
        return this.jobLogService.deleteJobLog(id);
    }
}
