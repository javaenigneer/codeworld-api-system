package com.codeworld.fc.job.task;

import com.codeworld.fc.system.log.mapper.LogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClassName TestTask
 * Description TODO
 * Author Lenovo
 * Date 2020/9/22
 * Version 1.0
**/
@Component
public class TestTask {

    @Autowired(required = false)
    private LogMapper logMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(TestTask.class);

    public void test(String params) {
        LOGGER.info("我是带参数的test方法，正在被执行，参数为：{}" , params);
    }
    public void test1() {
        LOGGER.info("我是不带参数的test1方法，正在被执行");
    }

    public void clearSystemLog(){
        // 定时清理日志
        try {
            LOGGER.info("日志清理开始");

            this.logMapper.deleteAllLog();

            LOGGER.info("日志清理完成");

        }catch (Exception e){

            LOGGER.error("日志清理失败---系统错误");
        }
    }
}
