package com.icezhg.sunflower.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Collection;

/**
 * Created by zhongjibing on 2022/08/23.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfo {
    private String id;
    private String username;
    private String name;
    private String nickname;
    private String gender;
    private String birthdate;
    private String picture;
    private String profile;
    private String email;
    private String mobile;
    private String createTime;
    private String updateTime;
    private Collection<String> authorities;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Collection<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<String> authorities) {
        this.authorities = authorities;
    }

    public static UserInfoBuilder builder() {
        return new UserInfoBuilder();
    }

    public static class UserInfoBuilder {

        private String id;
        private String username;
        private String name;
        private String nickname;
        private String gender;
        private String birthdate;
        private String picture;
        private String profile;
        private String email;
        private String mobile;
        private String createTime;
        private String updateTime;
        private Collection<String> authorities;

        private UserInfoBuilder() {
        }

        public UserInfoBuilder id(String id) {
            this.id = id;
            return this;
        }

        public UserInfoBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserInfoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserInfoBuilder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public UserInfoBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public UserInfoBuilder birthdate(String birthdate) {
            this.birthdate = birthdate;
            return this;
        }

        public UserInfoBuilder picture(String picture) {
            this.picture = picture;
            return this;
        }

        public UserInfoBuilder profile(String profile) {
            this.profile = profile;
            return this;
        }

        public UserInfoBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserInfoBuilder mobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public UserInfoBuilder createTime(String createTime) {
            this.createTime = createTime;
            return this;
        }

        public UserInfoBuilder updateTime(String updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        public UserInfoBuilder authorities(Collection<String> authorities) {
            this.authorities = authorities;
            return this;
        }

        public UserInfo build() {
            UserInfo userinfo = new UserInfo();
            userinfo.setId(this.id);
            userinfo.setUsername(this.username);
            userinfo.setName(this.name);
            userinfo.setNickname(this.nickname);
            userinfo.setGender(this.gender);
            userinfo.setBirthdate(this.birthdate);
            userinfo.setPicture(this.picture);
            userinfo.setProfile(this.profile);
            userinfo.setEmail(this.email);
            userinfo.setMobile(this.mobile);
            userinfo.setCreateTime(this.createTime);
            userinfo.setUpdateTime(this.updateTime);
            userinfo.setAuthorities(this.authorities);
            return userinfo;
        }
    }
}
