package com.icezhg.sunflower.security.filter;

import com.icezhg.sunflower.util.SecurityUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * Created by zhongjibing on 2023/06/25.
 */
public abstract class AuthenticatedRequestFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        if (SecurityUtil.authenticated()) {
            Consumer<HttpServletRequest> consumer = requestConsumer();
            if (consumer != null) {
                consumer.accept(request);
            }
        }

        chain.doFilter(request, response);
    }

    protected abstract Consumer<HttpServletRequest> requestConsumer();
}
