package com.codeworld.fc.monitor.service;

import com.codeworld.fc.common.response.DataResponse;
import com.codeworld.fc.common.response.FCResponse;
import com.codeworld.fc.system.user.dto.UserInfoResponse;

import java.util.List;

public interface ActiveUserService {
    /**
     * 获取在线用户数量
     * @return
     */
    FCResponse<Integer> getActiveUserCount();

    /**
     * 获取全部在线用户信息
     * @return
     */
    FCResponse<DataResponse<List<UserInfoResponse>>> getAllActiveUser();

    /**
     * 剔除用户下线
     * @param userId
     * @return
     */
    FCResponse<Void> offlineUser(Long userId);
}
