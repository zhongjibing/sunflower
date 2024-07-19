package com.icezhg.sunflower.enums;

/**
 * Created by zhongjibing on 2023/07/19.
 */
public enum LoginMethod {
    WEB(0, "Web"),
    WX(1, "WeChat");

    private final int method;
    private final String name;

    LoginMethod(int method, String name) {
        this.method = method;
        this.name = name;
    }

    public int getMethod() {
        return method;
    }

    public String getName() {
        return name;
    }
}
