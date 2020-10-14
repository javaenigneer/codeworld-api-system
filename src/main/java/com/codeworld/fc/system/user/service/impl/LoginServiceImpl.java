package com.codeworld.fc.system.user.service.impl;

import com.codeworld.fc.common.authority.JWTUtil;
import com.codeworld.fc.common.authority.JwtAuthenticationToken;
import com.codeworld.fc.common.enums.HttpFcStatus;
import com.codeworld.fc.common.enums.HttpMsg;
import com.codeworld.fc.common.enums.StatusEnum;
import com.codeworld.fc.common.exception.FCException;
import com.codeworld.fc.common.response.FCResponse;
import com.codeworld.fc.monitor.entity.LoginLog;
import com.codeworld.fc.monitor.mapper.LoginLogMapper;
import com.codeworld.fc.monitor.service.LoginLogService;
import com.codeworld.fc.system.user.entity.User;
import com.codeworld.fc.system.user.mapper.UserMapper;
import com.codeworld.fc.system.user.service.LoginService;
import com.codeworld.fc.system.user.vo.UserLoginOutRequest;
import com.codeworld.fc.system.user.vo.UserLoginRequest;
import com.codeworld.fc.utils.CookieUtils;
import com.codeworld.fc.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName LoginServiceImpl
 * Description 登录Service
 * Author Lenovo
 * Date 2020/8/25
 * Version 1.0
 **/
@Service
public class LoginServiceImpl implements LoginService {


    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private AuthenticationManager authenticationManager;

    @Autowired(required = false)
    private LoginLogService loginLogService;

    /**
     * 用户登录操作
     *
     * @param userLoginRequest
     * @return
     */
    @Override
    public FCResponse<Map<String, Object>> login(UserLoginRequest userLoginRequest, HttpServletRequest request, HttpServletResponse response) {

        String token = null;

        try {
            // 执行登录
            JwtAuthenticationToken authenticationToken = SecurityUtils.login(request,userLoginRequest,authenticationManager);

//            // 根据用户名获取用户信息
//            User user = this.userMapper.getUserByName(userLoginRequest.getUsername());
//
//            // 用户不存在
//            if (user == null) {
//
//                return FCResponse.dataResponse(HttpFcStatus.AUTHFAILCODE.getCode(), HttpMsg.user.USER_NO_EXIST.getMsg(), null);
//            }
//
//            // 判断用户是否被禁用
//            if (user.getUserStatus() == StatusEnum.USER_DISABLE) {
//
//                return FCResponse.dataResponse(HttpFcStatus.AUTHFAILCODE.getCode(), HttpMsg.user.USER_DISABLE.getMsg(), null);
//            }
//
//            // 存在
//            // 生成token
////            token = JWTUtil.sign(userLoginRequest.getUsername(), userLoginRequest.getPassword());
//
//            // 保存到Cookie中
//            CookieUtils.setCookie(request, response, "TOKEN", token, 3600);

            Map<String, Object> map = new HashMap<>();

            // 保存登录日志
            LoginLog loginLog = new LoginLog();

            loginLog.setSystemBrowserInfo();

            loginLog.setLoginLogName(authenticationToken.getPrincipal().toString());

            this.loginLogService.addLoginLog(loginLog);

            map.put("token", authenticationToken.getToken());

            return FCResponse.dataResponse(HttpFcStatus.DATASUCCESSGET.getCode(), HttpMsg.user.USER_LOGIN_SUCCESS.getMsg(), map);

        } catch (Exception e) {

            e.printStackTrace();

            throw new FCException("系统错误");
        }
    }

    /**
     * 退出登录
     *
     * @param userLoginOutRequest
     * @return
     */
    @Override
    public FCResponse<Void> loginOut(UserLoginOutRequest userLoginOutRequest) {

        return FCResponse.dataResponse(HttpFcStatus.DATASUCCESSGET.getCode(),HttpMsg.user.USER_LOGIN_OUT_SUCCESS.getMsg());
    }
}
