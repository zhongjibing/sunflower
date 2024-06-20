package com.icezhg.sunflower.pojo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by zhongjibing on 2022/09/10.
 */
@Setter
@Getter
public final class UserStatus {
    @NotNull
    @Min(1L)
    private Long userId;
    @NotNull
    private Integer status;

    public UserStatus() {
    }

    public UserStatus(Long userId, Integer status) {
        this.userId = userId;
        this.status = status;
    }

    public Long userId() {
        return userId;
    }

    public Integer status() {
        return status;
    }
}
