package com.codeworld.fc.system.log.service.impl;

import com.codeworld.fc.common.authority.JWTUtil;
import com.codeworld.fc.common.enums.HttpFcStatus;
import com.codeworld.fc.common.enums.HttpMsg;
import com.codeworld.fc.common.exception.FCException;
import com.codeworld.fc.common.response.DataResponse;
import com.codeworld.fc.common.response.FCResponse;
import com.codeworld.fc.system.log.entity.Log;
import com.codeworld.fc.system.log.mapper.LogMapper;
import com.codeworld.fc.system.log.service.LogService;
import com.codeworld.fc.system.log.vo.LogSearchVO;
import com.codeworld.fc.utils.AddressUtil;
import com.codeworld.fc.utils.CookieUtils;
import com.codeworld.fc.utils.IDGeneratorUtil;
import com.codeworld.fc.utils.IPUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.*;

/**
 * ClassName LogServiceImpl
 * Description TODO
 * Author Lenovo
 * Date 2020/9/20
 * Version 1.0
**/
@Service
public class LogServiceImpl implements LogService {

    @Autowired(required = false)
    private LogMapper logMapper;

    @Autowired(required = false)
    private ObjectMapper objectMapper;

    /**
     * 获取全部日志
     *
     * @param logSearchVO
     * @return
     */
    @Override
    public FCResponse<DataResponse<List<Log>>> getAllLog(LogSearchVO logSearchVO) {

        PageHelper.startPage(logSearchVO.getPage(),logSearchVO.getLimit());

        try {

            List<Log> logs = this.logMapper.getAllLog(logSearchVO);

            if (CollectionUtils.isEmpty(logs)){
                return FCResponse.dataResponse(HttpFcStatus.DATASUCCESSGET.getCode(),HttpMsg.log.LOG_DATA_EMPTY.getMsg(),DataResponse.dataResponse(logs,0L));
            }
            PageInfo<Log> pageInfo = new PageInfo<>(logs);
            return FCResponse.dataResponse(HttpFcStatus.DATASUCCESSGET.getCode(), HttpMsg.log.LOG_DATA_SUCCESS.getMsg(),DataResponse.dataResponse(pageInfo.getList(),pageInfo.getTotal()));

        }catch (Exception e){

            e.printStackTrace();

            throw new FCException("系统错误");
        }
    }

    /**
     * 添加日志
     *
     * @param point
     * @param targetMethod
     * @param request
     * @param operation
     * @param start
     */
    @Override
    public void addLog(ProceedingJoinPoint point, Method targetMethod, HttpServletRequest request, String operation, long start) {

        try {

            // 创建日志对象
            Log log = new Log();

            // 设置Id
            log.setLogId(IDGeneratorUtil.getNextId());

            // 设置操作用户
            // 获取Token
            String token = CookieUtils.getCookieValue(request, "Admin-Token");

            String userName = JWTUtil.getUserName(token);

            log.setLogOperationUserName(userName);

            // 设置耗时
            log.setLogTime(System.currentTimeMillis() - start);

            // 设置操作描述
            log.setLogOperation(operation);

            // 设置请求方法
            log.setLogMethod(request.getRequestURI());

            // 请求的方法参数值
            Object[] args = point.getArgs();
            // 请求的方法参数名称
            LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();

            String[] paramNames = u.getParameterNames(targetMethod);

            if (args != null && paramNames != null) {

                StringBuilder params = new StringBuilder();

                params = handleParams(params, args, Arrays.asList(paramNames));

                log.setLogParams(params.toString());
            }

            // 设置日志IP
            log.setLogIp(IPUtil.getIpAddr(request));

            // 设置地点
            log.setLogLocation(AddressUtil.getCityInfo(log.getLogIp()));

            // 设置日志时间
            log.setLogCreateTime(new Date());

            // 执行保存
            this.logMapper.addLog(log);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private StringBuilder handleParams(StringBuilder params, Object[] args, List paramNames) {
        try {
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof Map) {
                    Set set = ((Map) args[i]).keySet();
                    List<Object> list = new ArrayList<>();
                    List<Object> paramList = new ArrayList<>();
                    for (Object key : set) {
                        list.add(((Map) args[i]).get(key));
                        paramList.add(key);
                    }
                    return handleParams(params, list.toArray(), paramList);
                } else {
                    if (args[i] instanceof Serializable) {
                        Class<?> aClass = args[i].getClass();
                        try {
                            aClass.getDeclaredMethod("toString", new Class[]{null});
                            // 如果不抛出 NoSuchMethodException 异常则存在 toString 方法 ，安全的 writeValueAsString ，否则 走 Object的 toString方法
                            params.append(" ").append(paramNames.get(i)).append(": ").append(objectMapper.writeValueAsString(args[i]));
                        } catch (NoSuchMethodException e) {
                            params.append(" ").append(paramNames.get(i)).append(": ").append(objectMapper.writeValueAsString(args[i].toString()));
                        }
                    } else if (args[i] instanceof MultipartFile) {
                        MultipartFile file = (MultipartFile) args[i];
                        params.append(" ").append(paramNames.get(i)).append(": ").append(file.getName());
                    } else {
                        params.append(" ").append(paramNames.get(i)).append(": ").append(args[i]);
                    }
                }
            }
        } catch (Exception ignore) {
            params.append("参数解析失败");
        }
        return params;
    }
}
