package org.andot.share.basic.components.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.andot.share.basic.components.utils.CurrentUserUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 字段自动填充
 * <a href="https://mp.baomidou.com/guide/auto-fill-metainfo.html">查看</a>
 * @author andot
 */
@Component
public class UserMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.fillStrategy(metaObject, "createdTime", Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        this.fillStrategy(metaObject, "createdPerson", CurrentUserUtil.getUserName());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.fillStrategy(metaObject, "updatedTime", Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        this.fillStrategy(metaObject, "updatedPerson", CurrentUserUtil.getUserName());
    }
}
