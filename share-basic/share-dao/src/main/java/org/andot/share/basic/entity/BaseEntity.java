package org.andot.share.basic.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 对象 基础类
 * @author andot
 */
@Data
public class BaseEntity implements Serializable {
    private Boolean disabled = true;
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;
    @TableField(fill = FieldFill.UPDATE)
    private Date updatedTime;
    @TableField(fill = FieldFill.INSERT)
    private String createdPerson;
    @TableField(fill = FieldFill.UPDATE)
    private String updatedPerson;
    private static final long serialVersionUID = 1L;
}
