package org.andot.share.basic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AppSystem extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long appSystemId;
    private String appSystemName;
    private String appSystemIcon;
    private String appSystemUrl;
    private String orderCode;
}
