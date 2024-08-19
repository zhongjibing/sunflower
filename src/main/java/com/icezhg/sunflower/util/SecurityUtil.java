package com.icezhg.sunflower.util;

import com.icezhg.sunflower.common.Constant;
import com.icezhg.sunflower.domain.User;
import com.icezhg.sunflower.security.UserDetail;
import com.icezhg.sunflower.security.UserInfo;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Set;

public class SecurityUtil {

    public static boolean authenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == null) {
            return false;
        }
        if (authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }

        return true;
    }

    public static void checkExceedAuthority(Long userId) {
        if (isRootUser(userId) && !isRootUser()) {
            throw new AccessDeniedException("Access denied");
        }
    }

    public static boolean isRootUser() {
        return isRootUser(currentUserId());
    }

    public static boolean isRootUser(Long userId) {
        return User.isRoot(userId);
    }

    public static boolean isRootUser(String userId) {
        return NumberUtils.isCreatable(userId) && isRootUser(NumberUtils.createLong(userId));
    }

    public static Long currentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetail detail) {
            String userId = detail.getId();
            return NumberUtils.isCreatable(userId) ? NumberUtils.createLong(userId): Constant.UNKNOWN_USER_ID;
        }
        return Constant.UNKNOWN_USER_ID;
    }

    public static String currentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails details) {
            return details.getUsername();
        }

        return "anonymousUser";
    }

    public static UserDetail currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetail detail) {
            return detail;
        }

        return UserDetail.builder()
                .id(String.valueOf(Constant.UNKNOWN_USER_ID))
                .name("anonymousUser")
                .authorities(Set.of(new SimpleGrantedAuthority("ROLE_ANONYMOUS")))
                .build();
    }

    public static UserInfo currentUserInfo() {
        UserDetail detail = currentUser();
        return UserInfo.builder()
                .id(detail.getId())
                .username(detail.getUsername())
                .name(detail.getName())
                .nickname(detail.getNickname())
                .gender(detail.getGender())
                .birthdate(detail.getBirthdate())
                .picture(detail.getAvatar())
                .email(detail.getEmail())
                .mobile(detail.getMobile())
                .createTime(detail.getCreateTime())
                .updateTime(detail.getUpdateTime())
                .authorities(detail.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .code(detail.getCode())
                .build();

    }


    public static CsrfToken getCsrfToken() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes servletRequestAttributes) {
            return (CsrfToken) servletRequestAttributes.getRequest().getAttribute(CsrfToken.class.getName());
        }
        return null;
    }

    public static String getSessionId() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes servletRequestAttributes) {
            HttpSession session = servletRequestAttributes.getRequest().getSession(false);
            if (session != null) {
                return session.getId();
            }
        }
        return "";
    }

    private static void addCsrfTokenHeader() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes servletRequestAttributes) {
            CsrfToken csrfToken =
                    (CsrfToken) servletRequestAttributes.getRequest().getAttribute(CsrfToken.class.getName());
            HttpServletResponse response = servletRequestAttributes.getResponse();
            if (csrfToken != null && response != null) {
                response.setHeader(csrfToken.getHeaderName(), csrfToken.getToken());
            }
        }
    }
}
