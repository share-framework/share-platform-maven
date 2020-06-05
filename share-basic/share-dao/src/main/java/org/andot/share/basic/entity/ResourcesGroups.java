package org.andot.share.basic.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResourcesGroups implements Serializable {
    private Integer resGroupId;
    private Byte resourcesType;
    private byte[] resourcesId;
    private byte[] groupsId;
}