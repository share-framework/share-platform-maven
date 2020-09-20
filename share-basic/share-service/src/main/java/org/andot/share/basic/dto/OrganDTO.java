package org.andot.share.basic.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrganDTO {
    private Integer organId;
    private String organName;
    private Byte organType;
    private Byte orderCode;
    private Integer organParentId;
    private String caption;
    List<OrganDTO> children;
}
