package org.andot.share.basic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author andot
 */
@Setter
@Getter
public class DictType extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 字典主键 */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 字典名称 */
    private String dictName;

    /** 字典类型 */
    private String dictType;

    /** 状态（0正常 1停用） */
    private String status;

}
