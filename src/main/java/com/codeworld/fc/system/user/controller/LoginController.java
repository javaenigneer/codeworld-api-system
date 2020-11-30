package com.codeworld.fc.system.user.controller;

import com.codeworld.fc.common.annotation.ControllerEndpoint;
import com.codeworld.fc.common.enums.HttpFcStatus;
import com.codeworld.fc.common.response.FCResponse;
import com.codeworld.fc.system.user.dto.UserInfoResponse;
import com.codeworld.fc.system.user.service.LoginService;
import com.codeworld.fc.system.user.service.UserService;
import com.codeworld.fc.system.user.vo.UserLoginOutRequest;
import com.codeworld.fc.system.user.vo.UserLoginRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName LoginConttoller
 * Description 登录接口管理
 * Author Lenovo
 * Date 2020/8/12
 * Version 1.0
 **/
@RestController
@RequestMapping("system-auth")
@Api(tags = "用户登录接口管理")
public class LoginController {

    @Autowired(required = false)
    private LoginService loginService;
    @Autowired(required = false)
    private UserService userService;


    @PostMapping("user-login")
    @ApiOperation("用户登录")
    @ControllerEndpoint(operation = "用户登录",exceptionMessage = "登录失败")
    public FCResponse<Map<String, Object>> login(@RequestBody UserLoginRequest userLoginRequest,
                                                 HttpServletRequest request,
                                                 HttpServletResponse response) {
        return this.loginService.login(userLoginRequest, request, response);
    }

    @PostMapping("user-login-out")
    @ApiOperation("退出登录")
    @ControllerEndpoint(operation = "退出登录",exceptionMessage = "退出登录失败")
    public FCResponse<Void> LoginOut(@RequestBody UserLoginOutRequest userLoginOutRequest){
        return this.loginService.loginOut(userLoginOutRequest);
    }

    @PostMapping(value = "get-user-info",produces = "application/json;charset=utf-8")
    @ApiOperation("获取用户信息")
    @ControllerEndpoint(operation = "获取用户信息",exceptionMessage = "获取用户信息失败")
    public FCResponse<UserInfoResponse> getUserInfo(@RequestBody UserLoginRequest userLoginRequest,
                                                    HttpServletRequest request,
                                                    HttpServletResponse response) {
        return this.userService.getUserInfo(userLoginRequest,request,response);
    }


}
