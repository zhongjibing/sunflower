package com.icezhg.sunflower.serial;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.icezhg.sunflower.security.UserDetail;
import org.springframework.security.core.GrantedAuthority;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailSerializer extends JsonSerializer<UserDetail> {

    @Override
    public void serialize(UserDetail value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStringField("id", value.getId());
        gen.writeStringField("username", value.getUsername());
        gen.writeStringField("openid", value.getOpenid());
        gen.writeStringField("nickname", value.getNickname());
        gen.writeStringField("name", value.getName());
        gen.writeStringField("gender", value.getGender());
        gen.writeStringField("birthdate", value.getBirthdate());
        gen.writeStringField("email", value.getEmail());
        gen.writeStringField("mobile", value.getMobile());
        gen.writeStringField("avatar", value.getAvatar());
        gen.writeStringField("createTime", value.getCreateTime());
        gen.writeStringField("updateTime", value.getUpdateTime());
        gen.writeStringField("authorities", formatAuthorities(value.getAuthorities()));
        gen.writeBooleanField("accountNonExpired", value.isAccountNonExpired());
        gen.writeBooleanField("accountNonLocked", value.isAccountNonLocked());
        gen.writeBooleanField("credentialsNonExpired", value.isCredentialsNonExpired());
        gen.writeObjectFieldStart("attributes");
        if (value.getAttributes() != null) {
            for (Map.Entry<String, String> entry : value.getAttributes().entrySet()) {
                if (entry.getKey() != null) {
                    gen.writeStringField(entry.getKey(), entry.getValue());
                }
            }
        }
        gen.writeEndObject();
        gen.writeNumberField("loginMethod", value.getLoginMethod());
    }

    @Override
    public void serializeWithType(UserDetail value, JsonGenerator gen, SerializerProvider serializers,
                                  TypeSerializer typeSer) throws IOException {
        WritableTypeId typeIdDef = typeSer.typeId(value, JsonToken.START_OBJECT);
        typeSer.writeTypePrefix(gen, typeIdDef);
        gen.setCurrentValue(value);
        serialize(value, gen, serializers);
        typeSer.writeTypeSuffix(gen, typeIdDef);
    }

    private String formatAuthorities(Collection<GrantedAuthority> authorities) {
        if (authorities == null) {
            return "";
        }

        return authorities.stream()
                .filter(Objects::nonNull)
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }
}
