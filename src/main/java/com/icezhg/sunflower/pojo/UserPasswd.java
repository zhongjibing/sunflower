package com.icezhg.sunflower.pojo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by zhongjibing on 2022/09/10.
 */
@Setter
@Getter
public final class UserPasswd {
    @NotNull
    @Min(1L)
    private Long userId;
    @NotBlank
    @Size(max = 32, min = 6)
    private String passwd;

    public UserPasswd() {
    }

    public UserPasswd(Long userId, String passwd) {
        this.userId = userId;
        this.passwd = passwd;
    }

    public Long userId() {
        return userId;
    }

    public String passwd() {
        return passwd;
    }
}
