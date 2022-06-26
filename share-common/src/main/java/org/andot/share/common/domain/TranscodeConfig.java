package org.andot.share.common.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class TranscodeConfig implements Serializable {
    private String poster = "00:00:00.001";				// 截取封面的时间			HH:mm:ss.[SSS]
    private String tsSeconds = "30";			// ts分片大小，单位是秒
    private String cutStart;			// 视频裁剪，开始时间		HH:mm:ss.[SSS]
    private String cutEnd;
}
