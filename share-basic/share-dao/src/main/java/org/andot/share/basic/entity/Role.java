package org.andot.share.basic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色数据实体
 * @author andot
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Role extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long roleId;
    private String roleName;
    private String roleCode;
    private Integer roleType;
    private Integer roleOrder;
}