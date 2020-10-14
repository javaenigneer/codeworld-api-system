package com.codeworld.fc.system.user.service;

import com.codeworld.fc.common.response.DataResponse;
import com.codeworld.fc.common.response.FCResponse;
import com.codeworld.fc.system.user.dto.UserDeptResponse;
import com.codeworld.fc.system.user.dto.UserInfoResponse;
import com.codeworld.fc.system.user.entity.User;
import com.codeworld.fc.system.user.vo.UserLoginRequest;
import com.codeworld.fc.system.user.vo.UserRegisterRequest;
import com.codeworld.fc.system.user.vo.UserSearchRequest;
import com.codeworld.fc.system.user.vo.UserUpdateRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {
    /**
     * 获取全部用户
     *
     * @return
     */
    FCResponse<DataResponse<List<User>>> getAllUser(UserSearchRequest userSearchRequest);

    /**
     * 修改用户状态
     *
     * @param userId
     * @param userStatus
     * @return
     */
    FCResponse<Void> updateUserStatus(Long userId, Integer userStatus);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    FCResponse<Void> deleteUser(Long userId);

    /**
     * 添加用户
     * @param userRegisterRequest
     * @return
     */
    FCResponse<Void> addUser(UserRegisterRequest userRegisterRequest);

    /**
     * 修改用户
     * @param userUpdateRequest
     * @return
     */
    FCResponse<Void> updateUser(UserUpdateRequest userUpdateRequest);

    /**
     * 根据用户名获取用户
     * @param userName
     * @return
     */
    FCResponse<User> getUserByName(String userName);

    /**
     * 获取用户信息
     * @return
     */
    FCResponse<UserInfoResponse> getUserInfo(UserLoginRequest userLoginRequest, HttpServletRequest request, HttpServletResponse response);

    /**
     * 根据部门Id获取用户
     * @param deptId
     * @return
     */
    FCResponse<List<UserDeptResponse>> getUserByDeptId(Long deptId);

    /**
     * 获取全部用户数量
     * @return
     */
    FCResponse<Long> getAllUserCount();
}
