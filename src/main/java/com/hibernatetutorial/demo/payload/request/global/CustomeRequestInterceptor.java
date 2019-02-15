package com.hibernatetutorial.demo.payload.request.global;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;

@Component
public class CustomeRequestInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomeRequestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = Instant.now().toEpochMilli();
        LOGGER.info("METHOD [{}]Request URL :: {} :: Start Time = {}", request.getMethod(), request.getRequestURL().toString(), Instant.now());
        request.setAttribute("startTime", startTime);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long startTime = Instant.now().toEpochMilli();
        LOGGER.info("METHOD [{}]Request URL:: {} :: Time Taken = {}", request.getMethod(),request.getRequestURL().toString(), (Instant.now().toEpochMilli() - startTime));
    }
}
