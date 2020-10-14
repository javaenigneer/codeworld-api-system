package com.codeworld.fc.monitor.service;

import com.codeworld.fc.common.response.DataResponse;
import com.codeworld.fc.common.response.FCResponse;
import com.codeworld.fc.monitor.entity.LoginLog;
import com.codeworld.fc.monitor.vo.LoginLogSearchVO;

import java.util.List;

public interface LoginLogService {

    /**
     * 添加登录日志
     * @param loginLog
     */
    void addLoginLog(LoginLog loginLog);

    /**
     * 获取登录日志
     * @return
     */
    FCResponse<DataResponse<List<LoginLog>>> getAllLoginLog(LoginLogSearchVO loginLogSearchVO);

    /**
     * 删除登录日志
     * @param id
     * @return
     */
    FCResponse<Void> deleteLoginLog(Long id);
}
