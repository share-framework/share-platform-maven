package org.andot.share.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.LinkedList;

/**
 * 使用jackson进行封装的json array工具方法
 * @author andot
 */
public class JSONArray extends LinkedList {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public JSONArray() {
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 返回当前集合的json字符串
     * @return  如果返回为null, 则表示抛出异常
     */
    public String toJsonString() {
        if(this.size() == 0){
            return "";
        }
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
