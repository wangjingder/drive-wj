package com.wang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerIntercepter()).addPathPatterns("/**").excludePathPatterns("/admin/to_login","/admin/login","/css/**","/fonts/**","/js/**","/images/**","/admin/signin.css","/user/**","/user/register","/user/fabu");
    }
}
