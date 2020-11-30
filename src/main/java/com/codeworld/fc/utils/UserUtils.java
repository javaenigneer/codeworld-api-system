package com.codeworld.fc.utils;

import com.codeworld.fc.system.user.dto.UserInfoResponse;

/**
 * ClassName UserUtils
 * Description 存储/获取当前线程的用户信息工具类
 * Author Lenovo
 * Date 2020/10/19
 * Version 1.0
**/
public class UserUtils {

    // 线程变量，存放User实体类信息，即使是静态的与其他线程也是隔离的
    private static ThreadLocal<UserInfoResponse> userInfoResponseThreadLocal = new ThreadLocal<UserInfoResponse>();

    // 从当前线程变量中获取用户信息
    public static UserInfoResponse getCurrentUser(){
        UserInfoResponse userInfoResponse = userInfoResponseThreadLocal.get();
        return userInfoResponse;
    }

    // 获取当前用户的Id
    public static Long getUserId(){
        UserInfoResponse userInfoResponse = userInfoResponseThreadLocal.get();
        if (userInfoResponse != null && userInfoResponse.getUserId() != null && userInfoResponse.getUserId() > 0){
            return userInfoResponse.getUserId();
        }
        return null;
    }

    // 为当前线程变量赋值上用户信息
    public static void setLoginUser(UserInfoResponse userInfoResponse){
        userInfoResponseThreadLocal.set(userInfoResponse);
    }

    // 清除线程变量
    public static void removeUser(){
        userInfoResponseThreadLocal.remove();
    }
}
