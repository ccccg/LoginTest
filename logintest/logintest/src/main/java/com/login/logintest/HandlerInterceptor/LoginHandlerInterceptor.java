package com.login.logintest.HandlerInterceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginHandlerInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception{
        System.out.println("进入拦截器");
        String url = request.getRequestURI();

        if(url.contains("/login") || url.contains("/regist")){
            return true;
        }

        HttpSession session = request.getSession();
        Object obj = session.getAttribute("user");
        if(obj != null){
            return true;
        }

        request.setAttribute("msg","please sign up first");
        System.out.println("请求被重定向了");
        request.getRequestDispatcher("login.html");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView)throws Exception{
        System.out.println("请求处理完成");
    }
}
