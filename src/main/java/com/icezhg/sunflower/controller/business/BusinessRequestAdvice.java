package com.icezhg.sunflower.controller.business;

import com.icezhg.encryptor.SMUtil;
import com.icezhg.sunflower.common.Constant;
import com.icezhg.sunflower.security.UserDetail;
import com.icezhg.sunflower.util.SecurityUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * Created by zhongjibing on 2023/07/26.
 */
@RestControllerAdvice("com.icezhg.sunflower.controller.business")
public class BusinessRequestAdvice extends RequestBodyAdviceAdapter {
    private static final Logger log = LoggerFactory.getLogger(BusinessRequestAdvice.class);

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return new HttpInputMessage() {

            @Override
            public HttpHeaders getHeaders() {
                return inputMessage.getHeaders();
            }

            @Override
            public InputStream getBody() throws IOException {
                if (!StringUtils.equals(inputMessage.getHeaders().getFirst("X-Data-Encrypt"), "true")) {
                    return inputMessage.getBody();
                }

                String data = IOUtils.toString(inputMessage.getBody(), StandardCharsets.UTF_8);
                String decrypted = SMUtil.sm4Decrypt(decryptKey(), data);
                log.info("request body: {}, decrypt: {}", data, decrypted);
                return new ByteArrayInputStream(decrypted.getBytes(StandardCharsets.UTF_8));

            }
        };
    }

    private String decryptKey() {
        UserDetail currentUser = SecurityUtil.currentUser();
        Map<String, String> attributes = currentUser.getAttributes();
        return attributes != null ? attributes.getOrDefault(Constant.AUTH_CODE, "") : "";
    }
}
