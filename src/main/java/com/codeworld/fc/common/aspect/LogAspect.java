package com.codeworld.fc.common.aspect;

import com.codeworld.fc.common.annotation.ControllerEndpoint;
import com.codeworld.fc.common.exception.FCException;
import com.codeworld.fc.system.log.service.LogService;
import com.codeworld.fc.utils.HttpContextUtil;
import com.codeworld.fc.utils.ValidateUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * ClassName LogAspect
 * Description TODO
 * Author Lenovo
 * Date 2020/9/20
 * Version 1.0
**/
@Component
@Aspect
public class LogAspect extends AspectSupport {

    @Autowired(required = false)
    private LogService logService;

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 定义切点
     */
    @Pointcut("@annotation(com.codeworld.fc.common.annotation.ControllerEndpoint)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws FCException {
        Object result;
        Method targetMethod = resolveMethod(point);
        ControllerEndpoint annotation = targetMethod.getAnnotation(ControllerEndpoint.class);
        String operation = annotation.operation();
        long start = System.currentTimeMillis();
        try {
            result = point.proceed();
            if (StringUtils.isNotBlank(operation)) {
                HttpServletRequest request = HttpContextUtil.getHttpServletRequest();

                this.logService.addLog(point, targetMethod, request, operation, start);
            }
            return result;
        } catch (Throwable throwable) {
            String exceptionMessage = annotation.exceptionMessage();
            String message = throwable.getMessage();
            String error = ValidateUtil.containChinese(message) ? exceptionMessage + "，" + message : exceptionMessage;
            throw new FCException(error);
        }
    }
}
