package com.codeworld.fc.monitor.controller;

import com.codeworld.fc.common.annotation.ControllerEndpoint;
import com.codeworld.fc.common.response.DataResponse;
import com.codeworld.fc.common.response.FCResponse;
import com.codeworld.fc.monitor.entity.LoginLog;
import com.codeworld.fc.monitor.service.LoginLogService;
import com.codeworld.fc.monitor.vo.LoginLogSearchVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName LoginLogControlller
 * Description 登录日志接口管理
 * Author Lenovo
 * Date 2020/10/10
 * Version 1.0
**/
@RestController
@RequestMapping("system-login-log")
public class LoginLogController {

    @Autowired(required = false)
    private LoginLogService loginLogService;


    @PostMapping("get-all-login-log")
    @ApiOperation("获取登录日志")
    @ControllerEndpoint(operation = "获取登录日志",exceptionMessage = "获取登录日志失败")
    public FCResponse<DataResponse<List<LoginLog>>> getAllLoginLog(@RequestBody LoginLogSearchVO loginLogSearchVO){
        return this.loginLogService.getAllLoginLog(loginLogSearchVO);
    }

    @PostMapping("delete-login-log")
    @ApiOperation("删除登录日志")
    @ControllerEndpoint(operation = "删除登录日志",exceptionMessage = "删除登录日志失败")
    public FCResponse<Void> deleteLoginLog(@RequestParam("id") Long id){
        return this.loginLogService.deleteLoginLog(id);
    }
}
