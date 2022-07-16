package org.andot.share.basic.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class DeptDTO {
    private Long deptId;
    private String deptName;
    private String deptCode;
    private String deptParentCode;
    /**
     * 部门类型
     * 1 正常部门 2 虚拟部门
     */
    private Integer deptType;
    private Integer sort;
    private String caption;
    List<DeptDTO> children;
    private Boolean hasChildren;
}
