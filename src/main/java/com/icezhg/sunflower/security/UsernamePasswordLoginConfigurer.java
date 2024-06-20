package com.icezhg.sunflower.security;

import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * Created by zhongjibing on 2023/06/20.
 */
public class UsernamePasswordLoginConfigurer<B extends HttpSecurityBuilder<B>, T extends UsernamePasswordLoginConfigurer<B, T>>
        extends AbstractHttpConfigurer<T, B> {

    private final UsernamePasswordAuthenticationFilter authFilter = new UsernamePasswordAuthenticationFilter();
    private String loginProcessingUrl = "/login";

    private final AuthenticationSuccessHandler defaultHandler = (request, response, authentication) -> {
    };

    private AuthenticationSuccessHandler successHandler = this.defaultHandler;
    private AuthenticationFailureHandler failureHandler;

    public UsernamePasswordLoginConfigurer() {
    }

    @Override
    public void init(B http) throws Exception {
        this.authFilter.setRequiresAuthenticationRequestMatcher(createLoginProcessingUrlMatcher(loginProcessingUrl));
        ApplicationContext context = http.getSharedObject(ApplicationContext.class);
        AuthorizeHttpRequestsConfigurer<B>.AuthorizationManagerRequestMatcherRegistry registry =
                new AuthorizeHttpRequestsConfigurer<B>(context).getRegistry();
        registry.requestMatchers(loginProcessingUrl).permitAll();
    }

    @Override
    public void configure(B http) throws Exception {
        this.authFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        if (this.successHandler != null) {
            this.authFilter.setAuthenticationSuccessHandler(this.successHandler);
        }
        if (this.failureHandler != null) {
            this.authFilter.setAuthenticationFailureHandler(this.failureHandler);
        }
        SessionAuthenticationStrategy sessionAuthenticationStrategy = http
                .getSharedObject(SessionAuthenticationStrategy.class);
        if (sessionAuthenticationStrategy != null) {
            this.authFilter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy);
        }
        RememberMeServices rememberMeServices = http.getSharedObject(RememberMeServices.class);
        if (rememberMeServices != null) {
            this.authFilter.setRememberMeServices(rememberMeServices);
        }
        SecurityContextRepository securityContextRepository = http.getSharedObject(SecurityContextRepository.class);
        if (securityContextRepository != null) {
            this.authFilter.setSecurityContextRepository(securityContextRepository);
        }
        this.authFilter.setSecurityContextHolderStrategy(getSecurityContextHolderStrategy());
        http.addFilter(postProcess(this.authFilter));
    }

    public T loginProcessingUrl(String loginProcessingUrl) {
        this.loginProcessingUrl = loginProcessingUrl;
        this.authFilter.setRequiresAuthenticationRequestMatcher(createLoginProcessingUrlMatcher(loginProcessingUrl));
        return getSelf();
    }

    public final T successHandler(AuthenticationSuccessHandler successHandler) {
        this.successHandler = successHandler;
        return getSelf();
    }

    public final T failureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.failureHandler = authenticationFailureHandler;
        return getSelf();
    }

    private RequestMatcher createLoginProcessingUrlMatcher(String loginProcessingUrl) {
        return new AntPathRequestMatcher(loginProcessingUrl, "POST");
    }

    @SuppressWarnings("unchecked")
    private T getSelf() {
        return (T) this;
    }

}
