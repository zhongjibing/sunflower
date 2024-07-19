package com.icezhg.sunflower.security.authorization;

import org.springframework.security.authorization.AuthorityAuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * Created by zhongjibing on 2023/06/27.
 */
public class AuthoritiesAuthorizationManager implements AuthorizationManager<Collection<String>> {

    private static final String GRANT_ALL = "*:*:*";


    @Override
    public AuthorityAuthorizationDecision check(Supplier<Authentication> authentication, Collection<String> authorities) {
        boolean granted = isGranted(authentication.get(), authorities);
        return new AuthorityAuthorizationDecision(granted, AuthorityUtils.createAuthorityList(authorities));
    }

    private boolean isGranted(Authentication authentication, Collection<String> authorities) {
        return authentication != null && isAuthorized(authentication, authorities);
    }

    private boolean isAuthorized(Authentication authentication, Collection<String> authorities) {
        for (GrantedAuthority grantedAuthority : getGrantedAuthorities(authentication)) {
            if (GRANT_ALL.equals(grantedAuthority.getAuthority())) {
                return true;
            }
            if (authorities.contains(grantedAuthority.getAuthority())) {
                return true;
            }
        }
        return false;
    }

    private Collection<? extends GrantedAuthority> getGrantedAuthorities(Authentication authentication) {
        return authentication.getAuthorities();
    }

}
