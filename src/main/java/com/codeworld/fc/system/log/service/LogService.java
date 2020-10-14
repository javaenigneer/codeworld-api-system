package com.codeworld.fc.system.log.service;

import com.codeworld.fc.common.response.DataResponse;
import com.codeworld.fc.common.response.FCResponse;
import com.codeworld.fc.system.log.entity.Log;
import com.codeworld.fc.system.log.vo.LogSearchVO;
import org.aspectj.lang.ProceedingJoinPoint;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;

public interface LogService {
    /**
     * 获取全部日志
     * @param logSearchVO
     * @return
     */
    FCResponse<DataResponse<List<Log>>> getAllLog(LogSearchVO logSearchVO);

    /**
     * 添加日志
     * @param point
     * @param targetMethod
     * @param request
     * @param operation
     * @param start
     */
    void addLog(ProceedingJoinPoint point, Method targetMethod, HttpServletRequest request, String operation, long start);

    /**
     * 删除日志
     * @param id
     * @return
     */
    FCResponse<Void> deleteLog(Long id);
}
