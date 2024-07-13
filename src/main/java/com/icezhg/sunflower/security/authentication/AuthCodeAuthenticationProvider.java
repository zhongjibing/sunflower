package com.icezhg.sunflower.security.authentication;

import com.icezhg.sunflower.security.UserDetail;
import com.icezhg.sunflower.service.AuthCodeService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

/**
 * Created by zhongjibing on 2023/02/23.
 */
public class AuthCodeAuthenticationProvider implements AuthenticationProvider {

    private final AuthCodeService authCodeService;

    public AuthCodeAuthenticationProvider(AuthCodeService authCodeService) {
        Assert.notNull(authCodeService, "authCodeService can not be null");
        this.authCodeService = authCodeService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AuthCodeAuthenticationToken authenticationToken = (AuthCodeAuthenticationToken) authentication;
        if (authenticationToken.getPrincipal() instanceof String code) {
            UserDetails userDetails = authCodeService.auth(code);
            if (userDetails == null) {
                throw new UsernameNotFoundException("code: " + code);
            }
            authenticationToken = new AuthCodeAuthenticationToken(userDetails, true);
        }
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AuthCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
