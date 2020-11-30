package com.codeworld.fc.common.config;

import com.codeworld.fc.monitor.listener.ActiveUserListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName MywebConfig
 * Description TODO
 * Author Lenovo
 * Date 2020/10/16
 * Version 1.0
**/
@Configuration
public class MyWebConfig implements WebMvcConfigurer {

    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean() {

        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();

        servletListenerRegistrationBean.setListener(new ActiveUserListener());

        System.out.println("已监听");
        return servletListenerRegistrationBean;
    }
}
