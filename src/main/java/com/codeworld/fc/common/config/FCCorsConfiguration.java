package com.codeworld.fc.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域配置类
 * @author Lenovo
 */
@Configuration
public class FCCorsConfiguration {

    @Bean
    public CorsFilter corsFilter() {

        // 初始化cors配置对象
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // 设置跨域请求
        corsConfiguration.addAllowedOrigin("http://localhost:9572");
        corsConfiguration.addAllowedOrigin("http://192.168.2.6:9572");

        // 是否携带cookie
        corsConfiguration.setAllowCredentials(true);

        // 设置请求方法，*代表所有的请求方法--get、put、post
        corsConfiguration.addAllowedMethod("*");

        // 设置携带任何头信息
        corsConfiguration.addAllowedHeader("*");

        // 初始化cors源对象
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();

        // 设置跨域请求拦截
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        // 返回corsFilter实例，参数为：cors配置源对象
        return new CorsFilter(corsConfigurationSource);
    }
}
