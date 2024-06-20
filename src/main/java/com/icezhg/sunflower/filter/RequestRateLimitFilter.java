package com.icezhg.sunflower.filter;

import es.moki.ratelimitj.core.limiter.request.RequestLimitRule;
import es.moki.ratelimitj.core.limiter.request.RequestRateLimiter;
import es.moki.ratelimitj.inmemory.request.InMemorySlidingWindowRequestRateLimiter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Set;

/**
 * Created by zhongjibing on 2021/01/18
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class RequestRateLimitFilter extends OncePerRequestFilter {

    private final RequestMatcher loginRequestMatcher;
    private final RequestRateLimiter loginRateLimiter;
    private final RequestRateLimiter commonRateLimiter;

    public RequestRateLimitFilter() {
        loginRequestMatcher = new AntPathRequestMatcher("/login", "POST");
        Set<RequestLimitRule> loginRateRules = Collections.singleton(RequestLimitRule.of(Duration.ofSeconds(5), 1));
        loginRateLimiter = new InMemorySlidingWindowRequestRateLimiter(loginRateRules);

        Set<RequestLimitRule> commonRateRules = Collections.singleton(RequestLimitRule.of(Duration.ofSeconds(5), 10));
        commonRateLimiter = new InMemorySlidingWindowRequestRateLimiter(commonRateRules);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        RequestRateLimiter limiter = loginRequestMatcher.matches(request) ? loginRateLimiter : commonRateLimiter;
        if (limiter.overLimitWhenIncremented(request.getRequestURI())) {
            response.sendError(HttpStatus.TOO_MANY_REQUESTS.value());
            return;
        }

        chain.doFilter(request, response);
    }
}
