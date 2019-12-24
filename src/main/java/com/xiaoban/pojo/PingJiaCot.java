package com.xiaoban.pojo;

import lombok.Data;

/**
 * 评教内容
 */
@Data
public class PingJiaCot {
    private String kh;
    private String name;
    private String content;

    @Override
    public String toString() {
        String a ="";
        try{
            a= kh.split("-")[3];
        }catch (Exception e){

        }
        return "<tr>" +
                "<td>" + a +
                "</td><td>" + name  +
                "</td><td>" + content+
                "</td></tr>";
    }
}
