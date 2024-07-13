package com.icezhg.sunflower.serial;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.icezhg.sunflower.security.authentication.AuthCodeAuthenticationToken;

import java.io.IOException;

/**
 * Created by zhongjibing on 2023/02/23.
 */
public class AuthCodeAuthenticationTokenSerializer extends AbstractJsonSerializer<AuthCodeAuthenticationToken> {
    @Override
    public void serialize(AuthCodeAuthenticationToken value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        gen.writeBooleanField("authenticated", value.isAuthenticated());
        if (value.getPrincipal() instanceof String code) {
            gen.writeStringField("principal", code);
        } else {
            gen.writeObjectField("principal", value.getPrincipal());
        }
    }
}
