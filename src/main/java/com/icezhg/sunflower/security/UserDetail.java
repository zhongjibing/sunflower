package com.icezhg.sunflower.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.icezhg.sunflower.serial.UserDetailDeserializer;
import com.icezhg.sunflower.serial.UserDetailSerializer;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhongjibing on 2020/03/15
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonSerialize(using = UserDetailSerializer.class)
@JsonDeserialize(using = UserDetailDeserializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetail implements UserDetails, CredentialsContainer {
    @Serial
    private static final long serialVersionUID = 1329240769639891571L;

    private String password;
    private final String id;
    private final String username;
    private final String openid;
    private final String nickname;
    private final String name;
    private final String gender;
    private final String birthdate;
    private final String email;
    private final String mobile;
    private final String avatar;
    private final String createTime;
    private final String updateTime;
    private final Set<GrantedAuthority> authorities;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final Map<String, String> attributes;
    private final int loginMethod;
    private final String code;


    public UserDetail(String id, String username, String openid, String password, String name, String nickname,
                      String gender, String birthdate, String email, String mobile, String avatar, String createTime,
                      String updateTime, Set<GrantedAuthority> authorities, boolean accountNonExpired,
                      boolean accountNonLocked, boolean credentialsNonExpired, Map<String, String> attributes,
                      Integer loginMethod, String code) {
        this.id = id;
        this.username = username;
        this.openid = openid;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.gender = gender;
        this.birthdate = birthdate;
        this.email = email;
        this.mobile = mobile;
        this.avatar = avatar;
        this.createTime = createTime;
        this.updateTime = updateTime;
        if (authorities != null) {
            this.authorities = Set.copyOf(authorities);
        } else {
            this.authorities = Set.of();
        }
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.attributes = attributes != null ? Collections.unmodifiableMap(attributes) : null;
        this.loginMethod = loginMethod != null ? loginMethod : 0;
        this.code = code;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getOpenid() {
        return openid;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public int getLoginMethod() {
        return loginMethod;
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void eraseCredentials() {
        password = null;
    }

    public static UserDetailBuilder builder() {
        return new UserDetailBuilder();
    }

    public static class UserDetailBuilder {
        private String password;
        private String id;
        private String username;
        private String openid;
        private String nickname;
        private String name;

        private String gender;
        private String birthdate;
        private String email;
        private String mobile;
        private String avatar;
        private String createTime;
        private String updateTime;
        private Set<GrantedAuthority> authorities;
        private boolean accountNonExpired;
        private boolean accountNonLocked;
        private boolean credentialsNonExpired;

        private Map<String, String> attributes;
        private int loginMethod;
        private String code;

        public UserDetailBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserDetailBuilder id(String id) {
            this.id = id;
            return this;
        }

        public UserDetailBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserDetailBuilder openid(String openid) {
            this.openid = openid;
            return this;
        }

        public UserDetailBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserDetailBuilder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public UserDetailBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public UserDetailBuilder birthdate(String birthdate) {
            this.birthdate = birthdate;
            return this;
        }

        public UserDetailBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserDetailBuilder mobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public UserDetailBuilder avatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public UserDetailBuilder createTime(String createTime) {
            this.createTime = createTime;
            return this;
        }

        public UserDetailBuilder updateTime(String updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        public UserDetailBuilder authorities(Set<GrantedAuthority> authorities) {
            this.authorities = authorities;
            return this;
        }

        public UserDetailBuilder accountNonExpired(boolean accountNonExpired) {
            this.accountNonExpired = accountNonExpired;
            return this;
        }

        public UserDetailBuilder accountNonLocked(boolean accountNonLocked) {
            this.accountNonLocked = accountNonLocked;
            return this;
        }

        public UserDetailBuilder credentialsNonExpired(boolean credentialsNonExpired) {
            this.credentialsNonExpired = credentialsNonExpired;
            return this;
        }

        public UserDetailBuilder attributes(Map<String, String> attributes) {
            this.attributes = attributes;
            return this;
        }

        public UserDetailBuilder loginMethod(Integer loginMethod) {
            this.loginMethod = loginMethod != null ? loginMethod : 0;
            return this;
        }

        public UserDetailBuilder code(String code) {
            this.code = code;
            return this;
        }


        public UserDetail build() {
            return new UserDetail(id, username, openid, password, name, nickname, gender, birthdate, email, mobile, avatar,
                    createTime, updateTime, authorities, accountNonExpired, accountNonLocked, credentialsNonExpired,
                    attributes, loginMethod, code);
        }
    }

}
