package org.andot.share.basic.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrganDTO {
    private Long organId;
    private String organName;
    private String organCode;
    private String organParentCode;
    private Integer organType;
    private String organUrl;
    private Integer orderCode;
    private String caption;
    List<OrganDTO> children;
    private Boolean hasChildren;
}
