package com.icezhg.sunflower.security.filter;

import com.icezhg.captcha.Captcha;
import com.icezhg.captcha.CaptchaProducer;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by zhongjibing on 2022/09/02.
 */
public class CaptchaFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(CaptchaFilter.class);

    private static final RequestMatcher LOGIN_REQUEST_MATCHER = new AntPathRequestMatcher("/login", "POST");

    private static final RequestMatcher CAPTCHA_REQUEST_MATCHER = new AntPathRequestMatcher("/captcha", "GET");

    private static final String SESSION_KEY_CAPTCHA_CODE = "captcha_code";

    private static final String REQUEST_PARAM_VALIDATE_CODE = "validateCode";

    private static final String FAIL_URL = "/login?captcha";

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private final CaptchaProducer captchaProducer;

    public CaptchaFilter(CaptchaProducer captchaProducer) {
        Assert.notNull(captchaProducer, "captchaProducer should not be null");
        this.captchaProducer = captchaProducer;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        if (LOGIN_REQUEST_MATCHER.matches(request)) {
            if (validateCaptcha(request)) {
                filterChain.doFilter(request, response);
            } else {
                redirectStrategy.sendRedirect(request, response, FAIL_URL);
            }
        } else if (CAPTCHA_REQUEST_MATCHER.matches(request)) {
            sendCaptchaImage(request, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private boolean validateCaptcha(HttpServletRequest request) {
        String validateCode = request.getParameter(REQUEST_PARAM_VALIDATE_CODE);
        if (!StringUtils.hasText(validateCode)) {
            return false;
        }

        Object obj = request.getSession().getAttribute(SESSION_KEY_CAPTCHA_CODE);
        String code = obj != null ? String.valueOf(obj) : null;
        // 验证码清除，防止多次使用。
        request.getSession().removeAttribute(SESSION_KEY_CAPTCHA_CODE);
        if (code != null) {
            if (Calendar.getInstance().getTimeInMillis() > Long.parseLong(code.substring(code.indexOf("@") + 1))) {
                return false;
            }
            code = code.substring(0, code.indexOf("@"));
        }
        return validateCode.equalsIgnoreCase(code);
    }

    private void sendCaptchaImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        response.setContentType("image/jpeg");

        try (ServletOutputStream out = response.getOutputStream()) {
            Captcha captcha = captchaProducer.create();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.SECOND, 60);
            session.setAttribute(SESSION_KEY_CAPTCHA_CODE, captcha.getCode() + '@' + calendar.getTimeInMillis());
            ImageIO.write(captcha.getImage(), "jpg", out);
            out.flush();
        }
    }
}
