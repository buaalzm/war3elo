package com.war3elo.config;

import com.war3elo.filter.EncodingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lzm
 * @create 2021-03-08 23:08
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    //添加拦截器到spring mvc拦截器链
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new EncodingFilter()).addPathPatterns("/**");
    }
}