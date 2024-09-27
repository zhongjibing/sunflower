package com.icezhg.sunflower.visitor.pojo;

import java.util.Map;

/**
 * Created by zhongjibing on 2023/08/26.
 */
public interface WechatMessageData {
    WechatMessageData EMPTY = new WechatMessageData() {
    };

    default Map<String, Object> data() {
        return Map.of();
    }
}
