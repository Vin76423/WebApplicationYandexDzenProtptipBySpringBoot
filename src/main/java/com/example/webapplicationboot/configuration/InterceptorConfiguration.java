package com.example.webapplicationboot.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class InterceptorConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new PostInterceptor())
//                .addPathPatterns("/post/create");
//        registry.addInterceptor(new HomeInterсeptor())
//                .addPathPatterns("/home/reg")
//                .addPathPatterns("/home/auth");
//        registry.addInterceptor(new PostLikeInterceptor())
//                .addPathPatterns("/post/set-like")
//                .addPathPatterns("/post/like");
    }
}
