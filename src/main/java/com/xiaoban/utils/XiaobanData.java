package com.xiaoban.utils;

import lombok.Data;

/**
 * @Description:
 * @Author: xiaoban
 * @Date: 2019/8/22 5:16 PM
 */
@Data
public class XiaobanData {

    private Integer status;
    private String info;
    private Object data;


    public XiaobanData(Integer status, String info) {
        this.status = status;
        this.info = info;
    }

    public XiaobanData(Integer status, String info, Object data) {
        this.status = status;
        this.info = info;
        this.data = data;
    }

    public XiaobanData(Integer status, Object data) {
        this.status = status;
        this.data = data;
    }

    public XiaobanData(Integer status) {
        this.status = status;
        if (status==200){
            info = "请求成功!";
        }else {
            info = "请求失败";
        }
    }


    @Override
    public String toString() {
        return "{\"status\":"+status+
                ",\"info\":\""+info+"\""+
                ",\"\":\""+XiaobanJSON.getJson(data)+"\"";
    }
}