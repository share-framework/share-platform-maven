package org.andot.share.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.andot.share.basic.dao.XNumberPoolMapper;
import org.andot.share.basic.entity.XNumberPool;
import org.andot.share.basic.service.XNumberPoolService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 */
@RequiredArgsConstructor
@Service("xNumberPoolService")
public class XNumberPoolServiceImpl implements XNumberPoolService {

    private final XNumberPoolMapper xNumberPoolMapper;

    @Override
    public XNumberPool getMaxData() {
        XNumberPool xNumberPool = xNumberPoolMapper.getXNumberMaxData();
        return xNumberPool;
    }

    @Override
    public List<XNumberPool> batchAdd(Long count) {
        XNumberPool xNumberPool = xNumberPoolMapper.getXNumberMaxData();
        Long xNumber;
        if (Objects.isNull(xNumberPool)) {
            xNumber = 10001L;
        } else {
            xNumber = xNumberPool.getXNumber()+1;
        }
        List<XNumberPool> xNumberPools = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            XNumberPool xNumberPool1 = new XNumberPool();
            xNumberPool1.setUsed(0);
            xNumberPool1.setReserve(0);
            xNumberPool1.setXNumber(xNumber+i);
            int c = xNumberPoolMapper.insert(xNumberPool1);
            if (c>0) {
                xNumberPools.add(xNumberPool1);
            }
        }
        return xNumberPools;
    }

    @Override
    public List<XNumberPool> getXNumberPool(Integer count) {
        return xNumberPoolMapper.selectList(new LambdaQueryWrapper<XNumberPool>()
                .eq(XNumberPool::getUsed, 0)
                .eq(XNumberPool::getReserve, 0).last(" LIMIT " + count));
    }
}
