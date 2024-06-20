package com.icezhg.sunflower.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;

/**
 * Created by zhongjibing on 2021/01/16
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestUrlPrintFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(RequestUrlPrintFilter.class);

    private final ThreadLocal<StopWatch> watch = new ThreadLocal<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String query = request.getQueryString();
        String url = request.getRequestURL().toString() + (StringUtils.isBlank(query) ? "" : "?" + query);
        boolean debugEnabled = logger.isDebugEnabled();
        logger.info("handle[0]: [{}] {}{}", request.getMethod(), url, debugEnabled ? stringifyHeaders(request) : "");

        watch.set(new StopWatch());
        watch.get().start();

        try {
            chain.doFilter(request, response);
        } finally {
            watch.get().stop();

            logger.info("handle[1]: [{}] {}, response status: {}, duration: {} ms.{}",
                    request.getMethod(), url, response.getStatus(), watch.get().getTotalTimeMillis(),
                    debugEnabled ? stringifyHeaders(response) : "");
        }
    }

    private String stringifyHeaders(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        StringBuilder builder = new StringBuilder("\n");
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            builder.append(name).append(": ").append(request.getHeader(name)).append("\n");
        }
        return builder.toString();
    }

    private String stringifyHeaders(HttpServletResponse response) {
        Collection<String> headerNames = response.getHeaderNames();
        StringBuilder builder = new StringBuilder("\n");
        for (String name : headerNames) {
            builder.append(name).append(": ").append(response.getHeader(name)).append("\n");
        }
        return builder.toString();
    }
}
