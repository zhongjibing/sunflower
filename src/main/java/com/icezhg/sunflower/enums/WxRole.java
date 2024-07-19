package com.icezhg.sunflower.enums;

import java.util.Arrays;

/**
 * Created by zhongjibing on 2023/07/19.
 */
public enum WxRole {
    USER(0),
    ADMIN(1);

    private final int role;

    WxRole(int role) {
        this.role = role;
    }

    public int getRole() {
        return role;
    }

    public static WxRole roleOf(Integer role) {
        if (role == null) {
            return null;
        }

        return Arrays.stream(values()).filter(wxRole -> wxRole.getRole() == role).findFirst().orElse(null);
    }
}
