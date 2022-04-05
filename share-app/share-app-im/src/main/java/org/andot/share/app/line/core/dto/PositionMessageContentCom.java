package org.andot.share.app.line.core.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author andot
 */
@Setter
@Getter
public class PositionMessageContentCom extends ComLineMessageBody {
    private Double lang;
    private Double lat;
}