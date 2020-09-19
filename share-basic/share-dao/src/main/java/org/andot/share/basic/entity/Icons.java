package org.andot.share.basic.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 图标数据实体对象
 * @author lucas
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Icons {
    private Long id;
    private String brand;
    private Integer iconType;
    private String iconCode;
    private String iconUrl;
    private String iconCommit;
}
