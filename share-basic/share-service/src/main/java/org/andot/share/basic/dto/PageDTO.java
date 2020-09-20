package org.andot.share.basic.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class PageDTO<T> {
    private int page = 1;
    private int rows = 10;
    private T param;
}
