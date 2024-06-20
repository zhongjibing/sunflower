package com.icezhg.sunflower.common;

/**
 * Created by zhongjibing on 2020/03/21
 */
public interface Constant {

    String ROLE_PREFIX = "ROLE_";

    int TOKEN_EXPIRE_SECONDS = 30 * 60;

    long TOKEN_EXPIRE_MILLIS = TOKEN_EXPIRE_SECONDS * 1000L;

    String ATTRIBUTE_IP = "ip";
    String ATTRIBUTE_IP_LOCATION = "loc";

    String ATTRIBUTE_AGENT = "agent";

    String LOGIN_SUCCESS = "0";

    String LOGIN_FAILURE = "1";
}
