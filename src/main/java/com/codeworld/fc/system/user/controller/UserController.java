package com.codeworld.fc.system.user.controller;

import com.codeworld.fc.common.annotation.ControllerEndpoint;
import com.codeworld.fc.common.enums.HttpFcStatus;
import com.codeworld.fc.common.response.DataResponse;
import com.codeworld.fc.common.response.FCResponse;
import com.codeworld.fc.system.user.dto.UserDeptResponse;
import com.codeworld.fc.system.user.dto.UserInfoResponse;
import com.codeworld.fc.system.user.entity.User;
import com.codeworld.fc.system.user.entity.UserDept;
import com.codeworld.fc.system.user.service.UserDeptService;
import com.codeworld.fc.system.user.service.UserService;
import com.codeworld.fc.system.user.vo.UserLoginRequest;
import com.codeworld.fc.system.user.vo.UserRegisterRequest;
import com.codeworld.fc.system.user.vo.UserSearchRequest;
import com.codeworld.fc.system.user.vo.UserUpdateRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName UserController
 * Description 用户接口管理
 * Author Lenovo
 * Date 2020/8/12
 * Version 1.0
 **/
@RestController
@RequestMapping("system-user")
@Api(tags = "用户接口管理")
public class UserController {

    @Autowired(required = false)
    public UserService userService;

    @Autowired(required = false)
    private UserDeptService userDeptService;


    @PostMapping("get-all-user")
    @ApiOperation("获取全部用户")
    @PreAuthorize("hasAuthority('user')")
    @ControllerEndpoint(operation = "获取全部用户",exceptionMessage = "获取全部用户失败")
    public FCResponse<DataResponse<List<User>>> getAllUser(@RequestBody UserSearchRequest userSearchRequest) {
        return this.userService.getAllUser(userSearchRequest);
    }

    @PostMapping(value = "get-user-info",produces = "application/json;charset=utf-8")
    @ApiOperation("获取用户信息")
    @ControllerEndpoint(operation = "获取用户信息",exceptionMessage = "获取用户信息失败")
    public FCResponse<UserInfoResponse> getUserInfo(@RequestBody UserLoginRequest userLoginRequest,
                                                    HttpServletRequest request,
                                                    HttpServletResponse response) {
        return this.userService.getUserInfo(userLoginRequest,request,response);
    }

    @PostMapping("update-user-status")
    @ApiOperation("修改用户状态")
    @ControllerEndpoint(operation = "修改用户状态",exceptionMessage = "修改用户状态失败")
    public FCResponse<Void> updateUserStatus(@RequestParam("userId") Long userId,
                                             @RequestParam("status") Integer userStatus) {
        return this.userService.updateUserStatus(userId, userStatus);
    }


    @PostMapping("delete-user")
    @ApiOperation("删除用户")
    @PreAuthorize("hasAuthority('user:delete')")
    @ControllerEndpoint(operation = "删除用户",exceptionMessage = "删除用户失败")
    public FCResponse<Void> deleteUser(@RequestParam("userId") Long userId) {
        return this.userService.deleteUser(userId);
    }

    @PostMapping("add-user")
    @ApiOperation("添加用户")
    @PreAuthorize("hasAuthority('user:add')")
    @ControllerEndpoint(operation = "添加用户",exceptionMessage = "添加用户失败")
    public FCResponse<Void> addUser(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        return this.userService.addUser(userRegisterRequest);
    }

    @PostMapping("update-user")
    @ApiOperation("修改用户")
    @PreAuthorize("hasAuthority('user:edit')")
    @ControllerEndpoint(operation = "修改用户",exceptionMessage = "修改用户失败")
    public FCResponse<Void> updateUser(@RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        return this.userService.updateUser(userUpdateRequest);
    }

    @GetMapping("get-user-name")
    @ApiOperation("根据用户名获取用户")
    @ControllerEndpoint(operation = "根据用户名获取用户",exceptionMessage = "根据用户名获取用户失败")
    public FCResponse<User> getUserByName(@RequestParam("userName") String userName) {
        return this.userService.getUserByName(userName);
    }

    @PostMapping("get-user-dept-id")
    @ApiOperation("根据部门Id获取用户信息")
    @ControllerEndpoint(operation = "根据部门Id获取用户信息",exceptionMessage = "根据部门Id获取用户信息失败")
    public FCResponse<List<UserDeptResponse>> getUserByDeptId(@RequestParam("deptId") Long deptId){
        return this.userService.getUserByDeptId(deptId);
    }


    @GetMapping("get-dept-user-id")
    @ApiOperation("根据用户Id获取部门Id")
    @ControllerEndpoint(operation = "根据用户Id获取部门Id",exceptionMessage = "根据用户Id获取部门Id失败")
    public FCResponse<List<UserDept>> getDeptIdByUserId(@RequestParam("userId") Long userId){
        return this.userDeptService.getDeptIdByUserId(userId);
    }

    @GetMapping("get-all-user-count")
    @ApiOperation("获取全部用户数量")
    @ControllerEndpoint(operation = "获取全部用户数量",exceptionMessage = "获取全部用户数量失败")
    public FCResponse<Long> getAllUserCount(){
        return this.userService.getAllUserCount();
    }
}
