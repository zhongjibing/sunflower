package com.icezhg.sunflower.security.configurer;

import com.icezhg.sunflower.security.filter.AuthenticatedRequestFilter;
import com.icezhg.sunflower.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.context.SecurityContextHolderFilter;

import java.util.Date;
import java.util.function.Consumer;

/**
 * Created by zhongjibing on 2023/06/25.
 */
public class AuthenticatedRequestConfigurer<B extends HttpSecurityBuilder<B>> extends AbstractHttpConfigurer<AuthenticatedRequestConfigurer<B>, B> {

    private AuthenticatedRequestFilter requestFilter;

    @Override
    public void init(B http) throws Exception {
        ApplicationContext context = http.getSharedObject(ApplicationContext.class);
        SessionService service = context.getBean(SessionService.class);
        this.requestFilter = new AuthenticatedRequestFilter() {
            @Override
            protected Consumer<HttpServletRequest> requestConsumer() {
                return request -> {
                    HttpSession session = request.getSession(false);
                    if (session != null) {
                        service.updateLastAccessedTime(session.getId(), new Date());
                    }
                };
            }
        };
    }

    @Override
    public void configure(B http) throws Exception {
        AuthenticatedRequestFilter filter = postProcess(requestFilter);
        http.addFilterAfter(filter, SecurityContextHolderFilter.class);
    }
}
