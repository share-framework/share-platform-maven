package org.andot.share.common.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 基础分页参数
 *
 * @author gavin
 */
@Setter
@Getter
public class BasePageParams {
    private int rows;
    private int page;
}
