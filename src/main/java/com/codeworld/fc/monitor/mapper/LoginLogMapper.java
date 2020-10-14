package com.codeworld.fc.monitor.mapper;

import com.codeworld.fc.monitor.entity.LoginLog;
import com.codeworld.fc.monitor.vo.LoginLogSearchVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LoginLogMapper {

    /**
     * 保存登录日志
     * @param loginLog
     */
    void addLoginLog(LoginLog loginLog);

    /**
     * 获取登录日志
     * @param loginLogSearchVO
     * @return
     */
    List<LoginLog> getAllLoginLog(LoginLogSearchVO loginLogSearchVO);

    /**
     * 删除登录日志
     * @param id
     */
    void deleteLoginLog(Long id);
}
