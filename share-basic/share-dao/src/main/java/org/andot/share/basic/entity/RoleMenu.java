package org.andot.share.basic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author andot
 */
@Setter
@Getter
@ToString
public class RoleMenu {
    @TableId(type = IdType.AUTO)
    private Long roleMenuId;
    private String roleCode;
    private String menuCode;
}
