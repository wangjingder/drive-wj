package com.wang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerIntercepter implements HandlerInterceptor {

    //目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("userName");
        System.out.println("session:  "+user);
        if(user == null){
            //未登陆，返回登陆页面
            request.setAttribute("msg","没有权限请先登陆");
            System.out.println("LoginHandlerInterceptor.preHandle" + "----------------未登入------拦截请求--------------->");
            //request.getRequestDispatcher("/tologin.html").forward(request,response);
            response.sendRedirect("/admin/login");
            return false;
        }else{
            System.out.println("session:  "+user);
            //已登陆，放行请求
            return true;
        }
    }
}
