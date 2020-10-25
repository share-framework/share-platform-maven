package org.andot.share.basic.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {
    private Boolean disabled = true;
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;
    @TableField(fill = FieldFill.UPDATE)
    private Date updatedTime;
    private Date deletedTime;
    @TableField(fill = FieldFill.INSERT)
    private String createdPerson;
    @TableField(fill = FieldFill.UPDATE)
    private String updatedPerson;
    private String deletedPerson;
    private static final long serialVersionUID = 1L;
}
