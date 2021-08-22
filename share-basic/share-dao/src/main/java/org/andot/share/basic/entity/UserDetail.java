package org.andot.share.basic.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
