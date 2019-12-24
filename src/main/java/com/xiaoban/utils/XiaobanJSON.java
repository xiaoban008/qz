package com.xiaoban.utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: JSON互转类
 * @Author: xiaoban
 * @Date: 2019/7/19 12:21 AM
 */

public class XiaobanJSON {
    /*
     * 001.json转换成对象
     * @param:传入对象，json字符串
     * @return:Object
     */
    private static Logger log= LoggerFactory.getLogger(XiaobanJSON.class);
    private static  ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    public static <T> T readValue(String content, Class<T> valueType) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        try {
            //字符串转Json对象
            return objectMapper.readValue(content, valueType);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("JSON -> Object 转换失败!");
        }

        return null;
    }
    /*
     * 002.对象转换成json
     * @param:传入对象
     * @return:json字符串
     */
    public static  String getJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("Object -> JSON 转换失败!");
            return null;
        }
    }

    public static Map getMap(String json){
        Map<String, String> map = new HashMap<String, String>();
        ObjectMapper mapper = new ObjectMapper();

        try{
            map = mapper.readValue(json, new TypeReference<HashMap<String,String>>(){});
        }catch(Exception e){
            e.printStackTrace();
        }
        return map;
    }
}