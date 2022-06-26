package org.andot.share.common.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@ToString
public class MediaInfo implements Serializable {
    public static class Format {
        @SerializedName("bit_rate")
        private String bitRate;
        public String getBitRate() {
            return bitRate;
        }
        public void setBitRate(String bitRate) {
            this.bitRate = bitRate;
        }
    }

    public static class Stream {
        @SerializedName("index")
        private int index;

        @SerializedName("codec_name")
        private String codecName;

        @SerializedName("codec_long_name")
        private String codecLongame;

        @SerializedName("profile")
        private String profile;
    }

    @SerializedName("streams")
    private List<Stream> streams;

    @SerializedName("format")
    private Format format;
}
