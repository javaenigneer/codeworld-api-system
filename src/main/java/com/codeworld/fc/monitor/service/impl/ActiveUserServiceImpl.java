package com.codeworld.fc.monitor.service.impl;

import com.codeworld.fc.common.enums.HttpFcStatus;
import com.codeworld.fc.common.enums.HttpMsg;
import com.codeworld.fc.common.enums.StatusEnum;
import com.codeworld.fc.common.exception.FCException;
import com.codeworld.fc.common.response.DataResponse;
import com.codeworld.fc.common.response.FCResponse;
import com.codeworld.fc.monitor.listener.ActiveUserListener;
import com.codeworld.fc.monitor.service.ActiveUserService;
import com.codeworld.fc.system.user.dto.UserInfoResponse;
import com.codeworld.fc.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName ActiveUserServiceImpl
 * Description TODO
 * Author Lenovo
 * Date 2020/10/16
 * Version 1.0
 **/
@Service
public class ActiveUserServiceImpl implements ActiveUserService {

    @Autowired(required = false)
    private StringRedisTemplate stringRedisTemplate;

    private final String USER_INFO = "USER_INFO:USER:ID:";

    /**
     * 获取在线用户数量
     *
     * @return
     */
    @Override
    public FCResponse<Integer> getActiveUserCount() {
        return FCResponse.dataResponse(HttpFcStatus.DATASUCCESSGET.getCode(), HttpMsg.activeUser.ACTIVE_USER_COUNT_GET_SUCCESS.getMsg(), ActiveUserListener.userCount.get());
    }

    /**
     * 获取全部在线用户信息
     *
     * @return
     */
    @Override
    public FCResponse<DataResponse<List<UserInfoResponse>>> getAllActiveUser() {

        List<UserInfoResponse> userInfoResponses = new ArrayList<>(10);
        try {
            Set<String> keys = this.stringRedisTemplate.keys("*");
            Iterator<String> iterator = keys.iterator();
            while (iterator.hasNext()) {
                String json = this.stringRedisTemplate.opsForValue().get(iterator.next());
                UserInfoResponse userInfoResponse = JsonUtils.parse(json, UserInfoResponse.class);
                userInfoResponses.add(userInfoResponse);
            }
            return FCResponse.dataResponse(HttpFcStatus.DATASUCCESSGET.getCode(), HttpMsg.activeUser.ACTIVE_USER_GET_SUCCESS.getMsg(), DataResponse.dataResponse(userInfoResponses, (long) userInfoResponses.size()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new FCException("系统错误");
        }
    }

    /**
     * 剔除用户下线
     *
     * @param userId
     * @return
     */
    @Override
    public FCResponse<Void> offlineUser(Long userId) {

        if (userId == null || userId <= 0) {
            return FCResponse.dataResponse(HttpFcStatus.PARAMSERROR.getCode(), HttpMsg.user.USER_ID_ERROR.getMsg());
        }
        // 修改用户在线状态
        if (!this.stringRedisTemplate.hasKey(USER_INFO + userId)) {
            return FCResponse.dataResponse(HttpFcStatus.VALIDATEFAILCODE.getCode(), HttpMsg.activeUser.ACTIVE_USER_OFFLINE.getMsg());
        }
        try {
            String json = this.stringRedisTemplate.opsForValue().get(USER_INFO + userId);
            UserInfoResponse userInfoResponse = JsonUtils.parse(json, UserInfoResponse.class);
            userInfoResponse.setUserStatus(StatusEnum.USER_OFFLINE);
            json = JsonUtils.serialize(userInfoResponse);
            this.stringRedisTemplate.opsForValue().set(USER_INFO + userId, json, 20, TimeUnit.SECONDS);
            return FCResponse.dataResponse(HttpFcStatus.DATASUCCESSGET.getCode(), HttpMsg.activeUser.ACTIVE_USER_OFFLINE.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            throw new FCException("系统错误");
        }
    }
}
