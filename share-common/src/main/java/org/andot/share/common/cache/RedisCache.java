package org.andot.share.common.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisCache extends AbstractCache {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String getDataFotString(String key) {
        return null;
    }

    @Override
    public Number getDataFotNumber(String key) {
        return null;
    }

    @Override
    public String getDataFotString(Long key) {
        return null;
    }

    @Override
    public Number getDataFotNumber(Long key) {
        return null;
    }

    @Override
    public <T> boolean setData(T key, T value) {
        return false;
    }

    @Override
    public <T> boolean hSetData(T key, T item, T value) {
        return false;
    }
}
