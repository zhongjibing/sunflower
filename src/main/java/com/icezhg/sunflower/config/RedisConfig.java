package com.icezhg.sunflower.config;


import com.icezhg.sunflower.util.ObjectMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration(proxyBeanMethods = false)
public class RedisConfig {

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(RedisSerializer.string());
        template.setHashKeySerializer(RedisSerializer.string());
        template.setDefaultSerializer(Jackson2JsonRedisSerializerHolder.INSTANCE);
        template.setEnableDefaultSerializer(true);

        return template;
    }

    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
        return Jackson2JsonRedisSerializerHolder.INSTANCE;
    }

    private static class Jackson2JsonRedisSerializerHolder {
        private static final RedisSerializer<Object> INSTANCE;

        static {
            INSTANCE = new Jackson2JsonRedisSerializer<>(ObjectMapperFactory.getObjectMapper(), Object.class);
        }
    }
}
