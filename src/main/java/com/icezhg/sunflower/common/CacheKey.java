package com.icezhg.sunflower.common;

public interface CacheKey {

    String SESSION_KEY_PREFIX = "spring:session:sessions:";
    String SESSION_KEYS_PATTERN = SESSION_KEY_PREFIX + "????????-????-????-????-????????????";

    String SESSION_FIELD_LAST_ACCESSED_TIME = "lastAccessedTime";

    String ONLINE_USERS = "admin:monitor:online:users";
    String ONLINE_LOCK = "admin:monitor:online:lock";

    String IP_LOCATIONS = "admin:common:ip:locations";
}
