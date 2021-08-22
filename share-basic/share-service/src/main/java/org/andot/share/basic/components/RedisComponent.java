package org.andot.share.basic.components;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * redis组件类
 * @author andot
 */
@RequiredArgsConstructor
@Component
public class RedisComponent {

    private final RedisTemplate redisTemplate;

    /**
     * value 值
     * @param <K>
     * @param <V>
     * @return
     */
    public <K, V> ValueOperations<K, V> value () {
        return redisTemplate.opsForValue();
    }

    /**
     * hash 值
     * @param <K>
     * @param <V>
     * @return
     */
    public <H, K, V> HashOperations<H, K, V> hash () {
        return redisTemplate.opsForHash();
    }

    /**
     * 通用删除方法
     * @param key
     * @return
     */
    public boolean remove (String key) {
        return redisTemplate.delete(key);
    }
}
