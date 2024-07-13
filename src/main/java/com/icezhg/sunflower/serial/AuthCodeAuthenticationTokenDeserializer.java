package com.icezhg.sunflower.serial;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icezhg.sunflower.security.authentication.AuthCodeAuthenticationToken;

import java.io.IOException;

/**
 * Created by zhongjibing on 2023/02/23.
 */
public class AuthCodeAuthenticationTokenDeserializer extends AbstractJsonDeserializer<AuthCodeAuthenticationToken> {
    @Override
    public AuthCodeAuthenticationToken deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException,
            JacksonException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode jsonNode = mapper.readTree(jp);
        JsonNode principalNode = readJsonNode(jsonNode, "principal");
        Object principal;
        if (principalNode.isObject()) {
            principal = mapper.readValue(principalNode.traverse(mapper), Object.class);
        } else {
            principal = principalNode.asText();
        }
        AuthCodeAuthenticationToken result = new AuthCodeAuthenticationToken(principal);
        result.setAuthenticated(readJsonNode(jsonNode, "authenticated").asBoolean());
        return result;
    }
}
