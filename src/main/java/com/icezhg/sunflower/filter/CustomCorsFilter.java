package com.icezhg.sunflower.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Created by zhongjibing on 2021/10/16
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 2)
public class CustomCorsFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(CustomCorsFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (CorsUtils.isCorsRequest(request)) {
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, request.getHeader("Origin"));
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "POST, GET, PUT, DELETE, OPTIONS");
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
            response.setHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600");
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "User-Agent,X-Requested-With,Content-Type,X-CSRF-TOKEN");
            response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "X-CSRF-TOKEN, X-Captcha-Matched, X-Data-Encrypt");

            if (HttpMethod.OPTIONS.matches(request.getMethod())) {
                response.setStatus(HttpStatus.NO_CONTENT.value());
                return;
            }
        }

        chain.doFilter(request, response);
    }

}
