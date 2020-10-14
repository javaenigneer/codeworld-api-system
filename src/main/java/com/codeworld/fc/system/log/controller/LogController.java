package com.codeworld.fc.system.log.controller;

import com.codeworld.fc.common.annotation.ControllerEndpoint;
import com.codeworld.fc.common.response.DataResponse;
import com.codeworld.fc.common.response.FCResponse;
import com.codeworld.fc.system.log.entity.Log;
import com.codeworld.fc.system.log.service.LogService;
import com.codeworld.fc.system.log.vo.LogSearchVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName LogController
 * Description 日志接口管理
 * Author Lenovo
 * Date 2020/9/20
 * Version 1.0
**/
@RestController
@RequestMapping("system-log")
@Api(tags = "日志接口管理")
public class LogController {

    @Autowired(required = false)
    private LogService logService;

    @PostMapping("get-all-log")
    @ApiOperation("获取全部日志")
    @PreAuthorize("hasAuthority('log')")
    @ControllerEndpoint(operation = "获取全部日志",exceptionMessage = "日志获取失败")
    public FCResponse<DataResponse<List<Log>>> getAllLog(@RequestBody LogSearchVO logSearchVO){
        return this.logService.getAllLog(logSearchVO);
    }

    @PostMapping("delete-log")
    @ApiOperation("删除日志")
    @ControllerEndpoint(operation = "删除日志",exceptionMessage = "删除日志失败")
    public FCResponse<Void> deleteLog(@RequestParam("id") Long id){
        return this.logService.deleteLog(id);
    }
}
