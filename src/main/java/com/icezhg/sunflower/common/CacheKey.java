package com.icezhg.sunflower.common;

public interface CacheKey {
    String SESSION_KEYS_PATTERN = "spring:session:sessions:????????-????-????-????-????????????";

    String ONLINE_USERS = "admin:monitor:online:users";
    String ONLINE_LOCK = "admin:monitor:online:lock";

    String IP_LOCATIONS = "admin:common:ip:locations";
}
