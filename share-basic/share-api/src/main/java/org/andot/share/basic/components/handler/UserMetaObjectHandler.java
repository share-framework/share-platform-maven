package org.andot.share.basic.components.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.andot.share.basic.entity.User;
import org.andot.share.core.util.CurrentUserUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

/**
 * 字段自动填充
 * <a href="https://mp.baomidou.com/guide/auto-fill-metainfo.html">查看</a>
 * @author andot
 */
@Component
public class UserMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        if (Objects.isNull(CurrentUserUtil.userDetail())) {
            return;
        }
        this.fillStrategy(metaObject, "createdTime", Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        this.fillStrategy(metaObject, "createdPerson", CurrentUserUtil.getUserName());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (Objects.isNull(CurrentUserUtil.userDetail())) {
            return;
        }
        this.fillStrategy(metaObject, "updatedTime", Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        this.fillStrategy(metaObject, "updatedPerson", CurrentUserUtil.getUserName());
    }
}
