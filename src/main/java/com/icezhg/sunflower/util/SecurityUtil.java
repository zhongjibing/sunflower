package com.icezhg.sunflower.util;

import com.icezhg.sunflower.pojo.UserDetail;
import com.icezhg.sunflower.pojo.UserInfo;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

public class SecurityUtil {

    public static boolean authenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == null) {
            return false;
        }
        if (authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }

        addCsrfTokenHeader();

        return true;
    }

    public static Long currentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetail detail) {
            String userId = detail.getId();
            return StringUtils.hasText(userId) ? NumberUtils.parseNumber(userId, Long.class) : null;
        }
        return null;
    }

    public static String currentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails details) {
            return details.getUsername();
        }

        return "anonymousUser";
    }

    public static UserInfo currentUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null && authentication.getPrincipal() instanceof UserDetail detail) {
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
                    .build();
        }

        return UserInfo.builder()
                .id("")
                .name("anonymousUser")
                .authorities(List.of("ROLE_ANONYMOUS"))
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
