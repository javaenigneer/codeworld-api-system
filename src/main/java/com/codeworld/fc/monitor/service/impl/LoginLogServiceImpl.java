package com.codeworld.fc.monitor.service.impl;

import com.codeworld.fc.common.enums.HttpFcStatus;
import com.codeworld.fc.common.enums.HttpMsg;
import com.codeworld.fc.common.exception.FCException;
import com.codeworld.fc.common.response.DataResponse;
import com.codeworld.fc.common.response.FCResponse;
import com.codeworld.fc.monitor.entity.LoginLog;
import com.codeworld.fc.monitor.mapper.LoginLogMapper;
import com.codeworld.fc.monitor.service.LoginLogService;
import com.codeworld.fc.monitor.vo.LoginLogSearchVO;
import com.codeworld.fc.utils.AddressUtil;
import com.codeworld.fc.utils.HttpContextUtil;
import com.codeworld.fc.utils.IDGeneratorUtil;
import com.codeworld.fc.utils.IPUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * ClassName LoginLogServiceImpl
 * Description TODO
 * Author Lenovo
 * Date 2020/10/10
 * Version 1.0
 **/
@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired(required = false)
    private LoginLogMapper loginLogMapper;

    /**
     * 添加登录日志
     *
     * @param loginLog
     */
    @Override
    public void addLoginLog(LoginLog loginLog) {
        // 补全参数
        loginLog.setLoginLogId(IDGeneratorUtil.getNextId());

        loginLog.setLoginLogTime(new Date());

        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();

        loginLog.setLoginLogIp(IPUtil.getIpAddr(request));

        loginLog.setLoginLogLocation(AddressUtil.getCityInfo(IPUtil.getIpAddr(request)));

        this.loginLogMapper.addLoginLog(loginLog);
    }

    /**
     * 获取登录日志
     *
     * @return
     */
    @Override
    public FCResponse<DataResponse<List<LoginLog>>> getAllLoginLog(LoginLogSearchVO loginLogSearchVO) {

        PageHelper.startPage(loginLogSearchVO.getPage(), loginLogSearchVO.getLimit());

        List<LoginLog> loginLogs = this.loginLogMapper.getAllLoginLog(loginLogSearchVO);

        if (CollectionUtils.isEmpty(loginLogs)) {
            return FCResponse.dataResponse(HttpFcStatus.DATASUCCESSGET.getCode(), HttpMsg.loginLog.LOGIN_LOG_DATA_EMPTY.getMsg(), DataResponse.dataResponse(loginLogs, 0L));

        }

        PageInfo<LoginLog> pageInfo = new PageInfo<>(loginLogs);

        return FCResponse.dataResponse(HttpFcStatus.DATASUCCESSGET.getCode(), HttpMsg.loginLog.LOGIN_LOG_DATA_SUCCESS.getMsg(), DataResponse.dataResponse(pageInfo.getList(), pageInfo.getTotal()));
    }

    /**
     * 删除登录日志
     *
     * @param id
     * @return
     */
    @Override
    public FCResponse<Void> deleteLoginLog(Long id) {

        try {
            if (id == null || id <= 0) {
                return FCResponse.dataResponse(HttpFcStatus.PARAMSERROR.getCode(), HttpMsg.loginLog.LOGIN_LOG_PARAMS_ERROR.getMsg());

            }
            this.loginLogMapper.deleteLoginLog(id);

            return FCResponse.dataResponse(HttpFcStatus.DATASUCCESSGET.getCode(), HttpMsg.loginLog.LOGIN_LOG_DELETE_SUCCESS.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            throw new FCException("系统错误");
        }
    }
}
