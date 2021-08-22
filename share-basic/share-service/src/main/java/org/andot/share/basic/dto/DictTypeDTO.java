package org.andot.share.basic.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DictTypeDTO {

    private static final long serialVersionUID = 1L;

    /** 字典主键 */
    private Long id;

    /** 字典名称 */
    private String dictName;

    /** 字典类型 */
    private String dictType;

    /** 状态（0正常 1停用） */
    private Boolean status;

    /** 备注 */
    private String memo;
}
