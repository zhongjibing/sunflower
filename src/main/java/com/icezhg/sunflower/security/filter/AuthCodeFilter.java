package com.icezhg.sunflower.security.filter;

import com.icezhg.sunflower.security.authentication.AuthCodeAuthenticationToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.io.IOException;

/**
 * Created by zhongjibing on 2023/02/22.
 */
//@Component
public class AuthCodeFilter extends AbstractAuthenticationProcessingFilter {
    private static final String CODE_KEY = "code";
    private static final RequestMatcher REQUEST_MATCHER = new AntPathRequestMatcher("/auth", "POST");

    public AuthCodeFilter() {
        super(REQUEST_MATCHER);
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        String code = obtainCode(request);
        AuthCodeAuthenticationToken authentication = new AuthCodeAuthenticationToken(code);
        return this.getAuthenticationManager().authenticate(authentication);
    }

    protected String obtainCode(HttpServletRequest request) {
        return StringUtils.defaultString(request.getParameter(CODE_KEY));
    }
}
