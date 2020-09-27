package com.codeworld.fc.utils;

import com.codeworld.fc.common.authority.JWTUtil;
import com.codeworld.fc.common.authority.JwtAuthenticationToken;
import com.codeworld.fc.system.user.vo.UserLoginRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName SecurityUtils
 * Description Security相关操作
 * Author Lenovo
 * Date 2020/9/27
 * Version 1.0
**/
public class SecurityUtils {


    /**
     * 系统认证登录
     * @param request
     * @param userLoginRequest
     */
    public static JwtAuthenticationToken login(HttpServletRequest request, UserLoginRequest userLoginRequest, AuthenticationManager authenticationManager) {

        JwtAuthenticationToken token = new JwtAuthenticationToken(userLoginRequest.getUsername(),userLoginRequest.getPassword());
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // 执行登录过程
        Authentication authenticate = authenticationManager.authenticate(token);
        // 认证成功储存信息到上下文
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        // 生成令牌返回给客户端
        token.setToken(JWTUtil.sign(authenticate));
        return token;
    }


    /**
     * 获取用户名
     * @return
     */
    public static String getUsername(Authentication authentication) {
        String username = null;
        if(authentication != null) {
            Object principal = authentication.getPrincipal();
            if(principal != null && principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            }
        }
        return username;
    }
}
