package org.andot.shre.basic.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrganDto {
    private Integer organId;
    private String organName;
    private Byte organType;
    private Byte orderCode;
    private Integer organParentId;
    private String caption;
    List<OrganDto> children;
}
