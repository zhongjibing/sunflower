package com.icezhg.sunflower.controller.business;

import com.alibaba.fastjson2.JSONObject;
import com.icezhg.encryptor.SMUtil;
import com.icezhg.sunflower.common.Constant;
import com.icezhg.sunflower.security.UserDetail;
import com.icezhg.sunflower.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Map;

/**
 * Created by zhongjibing on 2023/07/26.
 */
//@RestControllerAdvice("com.icezhg.sunflower.controller.business")
public class BusinessResponseAdvice implements ResponseBodyAdvice<Object> {
    private static final Logger log = LoggerFactory.getLogger(BusinessResponseAdvice.class);

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        response.getHeaders().add("X-Data-Encrypt", "true");
        return SMUtil.sm4Encrypt(encryptKey(), JSONObject.toJSONString(body));
    }

    private String encryptKey() {
        UserDetail currentUser = SecurityUtil.currentUser();
        Map<String, String> attributes = currentUser.getAttributes();
        return attributes != null ? attributes.getOrDefault(Constant.AUTH_CODE, "") : "";
    }
}
