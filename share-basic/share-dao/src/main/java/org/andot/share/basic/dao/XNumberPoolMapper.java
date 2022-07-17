package org.andot.share.basic.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.andot.share.basic.entity.XNumberPool;
import org.springframework.stereotype.Repository;

@Repository
public interface XNumberPoolMapper extends BaseMapper<XNumberPool> {
    /**
     * 获取当前最大的number的数据
     * @return
     */
    XNumberPool getXNumberMaxData();
}
