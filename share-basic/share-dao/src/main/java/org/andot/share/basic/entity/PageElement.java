package org.andot.share.basic.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author andot
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PageElement extends BaseEntity {
    private Long pageElementId;
    private String pageElementName;
    private String pageElementCode;
    private Byte pageElementType;
    private String pageElementUrl;
}