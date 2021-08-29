package org.andot.share.line.core.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 消息对象
 * @author andot
 */
@Setter
@Getter
public class ComLineMessageFooter implements Serializable {
    private String version;
    private Long timestamp;
    private String clientName;
}
