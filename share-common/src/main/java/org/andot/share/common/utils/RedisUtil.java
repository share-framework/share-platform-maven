package org.andot.share.common.utils;

import lombok.RequiredArgsConstructor;
import org.andot.share.common.cache.RedisCache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Collection;
import java.util.List;

/**
 * redis工具类
 * @author andot
 */
@RequiredArgsConstructor
@Component
public class RedisUtil {

    private final RedisCache redisCache;

    private static RedisTemplate staticRedisTemplate;

    @PostConstruct
    public void init () {
        if (staticRedisTemplate == null) {
            staticRedisTemplate = this.redisCache.getRedis();
        }
    }

    /**
     * 单个value
     * @param key
     * @param t
     * @param <T>
     */
    public static  <T> void put (String key, T t) {
        staticRedisTemplate.opsForValue().set(key, t);
    }

    /**
     * 单个value
     * @param key
     * @param t
     * @param <T>
     */
    public static  <T> void put (String key, T t, Long expire) {
        staticRedisTemplate.opsForValue().set(key, t);
        staticRedisTemplate.expire(key, Duration.of(expire, ChronoUnit.SECONDS));
    }

    /**
     * 单个value
     * @param key
     * @param t
     * @param <T>
     * @return
     */
    public static  <T> T get (String key) {
        return (T) staticRedisTemplate.opsForValue().get(key);
    }

    /**
     * 通用删除方法
     * @param key
     * @return
     */
    public static boolean remove (String key) {
        return staticRedisTemplate.delete(key);
    }

    /**
     * 通用删除方法
     * @param keys
     * @return
     */
    public static Long remove (Collection<String> keys) {
        return staticRedisTemplate.delete(keys);
    }

    /**
     * 获得缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public static Collection<String> keys(final String pattern) {
        return staticRedisTemplate.keys(pattern);
    }
}

