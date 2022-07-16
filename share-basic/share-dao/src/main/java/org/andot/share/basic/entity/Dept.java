package org.andot.share.basic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@TableName("organ_dept")
@Data
@EqualsAndHashCode(callSuper = false)
public class Dept extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long deptId;
    private String deptName;
    /**
     * 部门类型
     * 1 正常部门 2 虚拟部门
     */
    private Integer deptType;
    private Integer sort;
    private String deptCode;
    private String deptParentCode;
    private String caption;
    private Integer isDel = 0;
}