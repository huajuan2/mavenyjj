package com.lanou.interceptor;

import com.lanou.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lanou on 2017/12/8.
 */
public class LoginInterceptor implements HandlerInterceptor {

    //最开始执行的
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        int num = -1;
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            num = 0;
        }else if (user != null){
            num = 1;
        }

        request.setAttribute("type",num);

        return false;
    }

    //中间执行的
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    //最后执行的
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
