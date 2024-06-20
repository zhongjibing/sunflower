package com.icezhg.sunflower.service.impl;

import com.icezhg.commons.cache.MemCache;
import com.icezhg.sunflower.service.CacheService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

@Service
public class CacheServiceImpl implements CacheService {

    private final MemCache<Map<String, Object>> cache;

    private final RedisTemplate<Object, Object> redisTemplate;

    public CacheServiceImpl(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.cache = new MemCache<>(this::buildCacheInfo);
    }

    @Override
    public Object getCacheInfo() {
        return cache.getData();
    }

    private Map<String, Object> buildCacheInfo() {
        Properties info = (Properties) redisTemplate.execute((RedisCallback<Object>) RedisServerCommands::info);
        Object dbSize = redisTemplate.execute((RedisCallback<Object>) RedisServerCommands::dbSize);
        Properties commandStats = (Properties) redisTemplate.execute((RedisCallback<Object>) connection -> connection.serverCommands().info("commandstats"));

        Map<String, Object> result = new HashMap<>(3);
        result.put("info", info);
        result.put("dbSize", dbSize);

        List<Map<String, String>> pieList = Optional.ofNullable(commandStats)
                .map(stats -> stats.stringPropertyNames().stream()
                        .map(key -> Map.of(
                                "name", StringUtils.removeStart(key, "cmdstat_"),
                                "value", StringUtils.substringBetween(commandStats.getProperty(key), "calls=", ",usec"))
                        ).toList())
                .orElse(List.of());
        result.put("commandStats", pieList);
        return result;
    }
}
