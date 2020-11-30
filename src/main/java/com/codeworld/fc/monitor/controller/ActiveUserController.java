package com.codeworld.fc.monitor.controller;

import com.codeworld.fc.common.annotation.ControllerEndpoint;
import com.codeworld.fc.common.response.DataResponse;
import com.codeworld.fc.common.response.FCResponse;
import com.codeworld.fc.monitor.service.ActiveUserService;
import com.codeworld.fc.system.user.dto.UserInfoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName ActiveUserController
 * Description 在线用户接口管理
 * Author Lenovo
 * Date 2020/10/16
 * Version 1.0
**/
@RestController
@RequestMapping("system-monitor")
@Api(tags = "在线用户管理接口")
public class ActiveUserController {

    @Autowired(required = false)
    private ActiveUserService activeUserService;


    @GetMapping("get-active-user-count")
    @ApiOperation("获取在线用户数量")
    @ControllerEndpoint(operation = "获取在线用户数量",exceptionMessage = "获取在线用户数量失败")
    public FCResponse<Integer> getActiveUserCount(){
        return this.activeUserService.getActiveUserCount();
    }

    @GetMapping("get-all-active-user")
    @ApiOperation("获取全部在线用户信息")
    @ControllerEndpoint(operation = "获取全部在线用户信息",exceptionMessage = "获取全部在线用户信息失败")
    public FCResponse<DataResponse<List<UserInfoResponse>>> getAllActiveUser(){
        return this.activeUserService.getAllActiveUser();
    }

    @GetMapping("offline-user-id")
    @ApiOperation("剔除用户下线")
    @ControllerEndpoint(operation = "剔除用户下线",exceptionMessage = "剔除用户下线失败")
    public FCResponse<Void> offlineUser(@RequestParam("userId") Long userId){
        return this.activeUserService.offlineUser(userId);
    }
}
