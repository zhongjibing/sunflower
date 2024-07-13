package com.icezhg.sunflower.security.configurer;

import com.icezhg.sunflower.security.authentication.AuthCodeAuthenticationProvider;
import com.icezhg.sunflower.security.authentication.NoopAuthenticationSuccessHandler;
import com.icezhg.sunflower.security.filter.AuthCodeFilter;
import com.icezhg.sunflower.service.AuthCodeService;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.AuthenticationEntryPointFailureHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

/**
 * Created by zhongjibing on 2023/02/23.
 */
public class AuthCodeConfigurer<B extends HttpSecurityBuilder<B>>
        extends AbstractHttpConfigurer<AuthCodeConfigurer<B>, B> {

    @Override
    public void configure(B http) throws Exception {
        AuthCodeFilter filter = new AuthCodeFilter();
        filter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        filter.setAuthenticationSuccessHandler(new NoopAuthenticationSuccessHandler());
        HttpStatusEntryPoint statusEntryPoint = new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED);
        filter.setAuthenticationFailureHandler(new AuthenticationEntryPointFailureHandler(statusEntryPoint));
        SessionAuthenticationStrategy sessionAuthenticationStrategy =
                http.getSharedObject(SessionAuthenticationStrategy.class);
        if (sessionAuthenticationStrategy != null) {
            filter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy);
        }
        SecurityContextRepository securityContextRepository = http.getSharedObject(SecurityContextRepository.class);
        if (securityContextRepository == null) {
            securityContextRepository = new DelegatingSecurityContextRepository(
                    new RequestAttributeSecurityContextRepository(), new HttpSessionSecurityContextRepository());
        }
        filter.setSecurityContextRepository(securityContextRepository);
        filter.setSecurityContextHolderStrategy(getSecurityContextHolderStrategy());
        http.addFilterAfter(filter, LogoutFilter.class);

        AuthCodeService authCodeService = http.getSharedObject(AuthCodeService.class);
        if (authCodeService == null) {
            ApplicationContext context = http.getSharedObject(ApplicationContext.class);
            authCodeService = context.getBean(AuthCodeService.class);
        }
        AuthCodeAuthenticationProvider provider = new AuthCodeAuthenticationProvider(authCodeService);
        http.authenticationProvider(provider);
    }

}
