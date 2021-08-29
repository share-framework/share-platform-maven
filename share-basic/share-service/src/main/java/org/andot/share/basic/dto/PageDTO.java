package org.andot.share.basic.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 分页对象
 * @author andot
 */
@Setter
@Getter
public class PageDTO<T> {
    private Integer page = 1;
    private Integer rows = 10;
    private T param;
}
