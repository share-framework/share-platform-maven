package org.andot.share.basic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 *
 * @author andot
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserDetail extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long xNumber;
    private String realName;
    private String introduction;
    private String sex;
    private Date birthday;
    private Boolean auth;
    private String domain;
    @TableField(exist = false)
    private List<String> permissions;
}
