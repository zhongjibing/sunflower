package com.icezhg.sunflower.enums;

/**
 * Created by zhongjibing on 2023/08/02.
 */
public enum ResourceSubType {
    NORMAL(0, "正常"),
    TOP_TABLE(1, "主桌"),
    SIDE_TABLE(2, "副桌");

    private final int type;
    private final String name;

    ResourceSubType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
