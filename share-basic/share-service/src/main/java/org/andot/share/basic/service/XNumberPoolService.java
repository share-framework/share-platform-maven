package org.andot.share.basic.service;

import org.andot.share.basic.entity.XNumberPool;

import java.util.List;

public interface XNumberPoolService {

    /**
     *
     * @return
     */
    XNumberPool getMaxData();

    /**
     * 批量生成账号数量
     * @param count 账号数量
     * @return
     */
    List<XNumberPool> batchAdd(Long count);

    /**
     * 查询未使用过的列表
     * @param count 查询数量
     * @return
     */
    List<XNumberPool> getXNumberPool(Integer count);
}
