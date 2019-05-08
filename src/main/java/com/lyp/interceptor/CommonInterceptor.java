package com.lyp.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommonInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String contextPath = httpServletRequest.getContextPath();
        String requestURI = httpServletRequest.getRequestURI();
        String url = requestURI.substring(contextPath.length());
        System.out.println("handlerInterceptor preHandle!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("url:"+url);
        if("/logins".equals(url)||"/login".equals(url)||"/toregister".equals(url)||"/register".equals(url)){
            return true;
        }else {
            System.out.println("非法登陆！返回大老爷们");
            if ((httpServletRequest.getSession().getAttribute("user"))!=null){
                System.out.println("session exist");
                return true;
            }else {
                System.out.println("forward login");
                httpServletRequest.getRequestDispatcher("/logins").forward(httpServletRequest,httpServletResponse);
                return  false;
            }
        }
     /* return true;*/
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
