package org.andot.shre.basic.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PageDto<T> {
    private int page = 1;
    private int rows = 10;
    private T params;
}
