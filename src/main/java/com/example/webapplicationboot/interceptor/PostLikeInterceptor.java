package com.example.webapplicationboot.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostLikeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("user") == null) {
            String postId = request.getParameter("post-id");
            response.sendRedirect(String.format("/post/show?post-id=%s", postId));
            return false;
        }
        return true;
    }
}
