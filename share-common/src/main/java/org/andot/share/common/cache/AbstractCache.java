package org.andot.share.common.cache;

import org.springframework.stereotype.Component;

/**
 * 抽象缓存工具类
 * @author andot
 */
@Component
public abstract class AbstractCache {

    public abstract String getDataFotString(String key);
    public abstract Number getDataFotNumber(String key);
    public abstract String getDataFotString(Long key);
    public abstract Number getDataFotNumber(Long key);
    public abstract <T> boolean setData(T key, T value);

    public abstract <T> boolean hSetData(T key, T item, T value);


}
