package org.andot.share.basic.utils;

import org.andot.share.basic.entity.DictData;
import org.andot.share.common.type.ConstantType;
import org.andot.share.common.utils.RedisUtil;

import java.util.Collection;
import java.util.List;

/**
 * 字典数据工具类
 * @author andot
 */
public class DictUtil {
    /**
     * 分隔符
     */
    public static final String SEPARATOR = ",";

    /**
     * 清空字典缓存
     */
    public static void clearDictCache() {
        Collection<String> keys = RedisUtil.keys(ConstantType.DICT_KEY + "*");
        RedisUtil.remove(keys);
    }

    /**
     * 设置字典缓存
     *
     * @param dictType 字典类型
     * @param dictDatas 字典数据列表
     */
    public static void setDictCache(String dictType, List<DictData> dictDatas) {
        RedisUtil.put(getCacheKey(dictType), dictDatas);
    }

    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    public static String getCacheKey(String configKey) {
        return ConstantType.DICT_KEY + configKey;
    }
}
