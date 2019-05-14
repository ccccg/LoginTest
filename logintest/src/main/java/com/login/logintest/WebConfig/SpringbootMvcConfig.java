package com.login.logintest.WebConfig;

import com.login.logintest.HandlerInterceptor.LoginHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringbootMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginHandlerInterceptor myHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry){
        interceptorRegistry.addInterceptor(myHandlerInterceptor).addPathPatterns("/aa/**");

    }
}
