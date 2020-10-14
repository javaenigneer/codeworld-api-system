package com.codeworld.fc.system.user.service;

import com.codeworld.fc.common.response.FCResponse;
import com.codeworld.fc.system.user.vo.UserLoginOutRequest;
import com.codeworld.fc.system.user.vo.UserLoginRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface LoginService {
    /**
     * 用户登录操作
     *
     * @param userLoginRequest
     * @return
     */
    FCResponse<Map<String, Object>> login(UserLoginRequest userLoginRequest, HttpServletRequest request, HttpServletResponse response);

    /**
     * 退出登录
     * @param userLoginOutRequest
     * @return
     */
    FCResponse<Void> loginOut(UserLoginOutRequest userLoginOutRequest);
}
