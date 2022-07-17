package org.andot.share.basic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class XNumberPool {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long xNumber;
    private Integer used;
    private Integer reserve;

}
