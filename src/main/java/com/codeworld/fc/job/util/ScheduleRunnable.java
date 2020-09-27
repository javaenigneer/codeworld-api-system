package com.codeworld.fc.job.util;

import com.codeworld.fc.utils.SpringContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * ClassName ScheduleRunnable
 * Description TODO
 * Author Lenovo
 * Date 2020/9/22
 * Version 1.0
**/
public class ScheduleRunnable implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleRunnable.class);
    private Object target;
    private Method method;
    private String params;

    ScheduleRunnable(String beanName, String methodName, String params) throws NoSuchMethodException, SecurityException {
        this.target = SpringContextUtil.getBean(beanName);
        this.params = params;

        if (StringUtils.isNotBlank(params)) {
            this.method = target.getClass().getDeclaredMethod(methodName, String.class);
        } else {
            this.method = target.getClass().getDeclaredMethod(methodName);
        }
    }

    @Override
    public void run() {
        try {
            ReflectionUtils.makeAccessible(method);
            if (StringUtils.isNotBlank(params)) {
                method.invoke(target, params);
            } else {
                method.invoke(target);
            }
        } catch (Exception e) {
            LOGGER.error("执行定时任务失败", e);
        }
    }
}
