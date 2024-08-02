package com.icezhg.sunflower.enums;

/**
 * Created by zhongjibing on 2023/08/03.
 */
public enum Channel {
    PC(0, "PC"),
    WX_MINI(1, "微信小程序");

    private final int channel;
    private final String name;

    Channel(int channel, String name) {
        this.channel = channel;
        this.name = name;
    }

    public int getChannel() {
        return channel;
    }

    public String getName() {
        return name;
    }
}
