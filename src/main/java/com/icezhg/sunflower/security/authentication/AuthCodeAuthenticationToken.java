package com.icezhg.sunflower.security.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;

/**
 * Created by zhongjibing on 2023/02/23.
 */
public class AuthCodeAuthenticationToken extends AbstractAuthenticationToken {
    @Serial
    private static final long serialVersionUID = 5300772247814820814L;

    private final Object principal;

    public AuthCodeAuthenticationToken(Object principal) {
        this(principal, false);
    }

    public AuthCodeAuthenticationToken(Object principal, boolean authenticated) {
        super(principal instanceof UserDetails details ? details.getAuthorities() : null);
        this.principal = principal;
        super.setAuthenticated(authenticated);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
