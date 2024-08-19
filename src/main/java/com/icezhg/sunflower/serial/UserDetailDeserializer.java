package com.icezhg.sunflower.serial;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.icezhg.sunflower.security.UserDetail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserDetailDeserializer extends JsonDeserializer<UserDetail> {

    private static final TypeReference<Set<SimpleGrantedAuthority>> SIMPLE_GRANTED_AUTHORITY_SET = new TypeReference<>() {
    };

    @Override
    public UserDetail deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode jsonNode = mapper.readTree(jp);
        return UserDetail.builder()
                .id(readJsonNode(jsonNode, "id").asText())
                .username(readJsonNode(jsonNode, "username").asText())
                .openid(readJsonNode(jsonNode, "openid").asText())
                .nickname(readJsonNode(jsonNode, "nickname").asText())
                .name(readJsonNode(jsonNode, "name").asText())
                .gender(readJsonNode(jsonNode, "gender").asText())
                .birthdate(readJsonNode(jsonNode, "birthdate").asText())
                .email(readJsonNode(jsonNode, "email").asText(""))
                .mobile(readJsonNode(jsonNode, "mobile").asText(""))
                .avatar(readJsonNode(jsonNode, "avatar").asText(""))
                .createTime(readJsonNode(jsonNode, "createTime").asText("0"))
                .updateTime(readJsonNode(jsonNode, "updateTime").asText("0"))
                .authorities(parseGrantedAuthorities(jsonNode.get("authorities").asText()))
                .accountNonExpired(readJsonNode(jsonNode, "accountNonExpired").asBoolean())
                .accountNonLocked(readJsonNode(jsonNode, "accountNonLocked").asBoolean())
                .credentialsNonExpired(readJsonNode(jsonNode, "credentialsNonExpired").asBoolean())
                .attributes(parseAttributes(readJsonNode(jsonNode, "attributes")))
                .loginMethod(readJsonNode(jsonNode, "loginMethod").asInt(0))
                .code(readJsonNode(jsonNode, "code").asText())
                .build();
    }

    private JsonNode readJsonNode(JsonNode jsonNode, String field) {
        return jsonNode.has(field) ? jsonNode.get(field) : MissingNode.getInstance();
    }

    private Set<GrantedAuthority> parseGrantedAuthorities(String value) {
        if (value == null || value.isBlank()) {
            return Set.of();
        }
        return Stream.of(value.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

    private Map<String, String> parseAttributes(JsonNode node) {
        Map<String, String> result = new HashMap<>();
        node.fieldNames().forEachRemaining(name -> result.put(name, node.get(name).asText("")));
        return Map.copyOf(result);
    }

}
