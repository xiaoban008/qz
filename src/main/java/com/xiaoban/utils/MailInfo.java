package com.xiaoban.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: xiaoban
 * @Date: 2019/7/20 2:21 PM
 */
@Component
@Data
public class MailInfo {
    private String to;  //收件人
    private String title;   //信标题
    private String msg;     //信件内容
    public void setMailInfo(String to,String title,String msg){
        this.to = to;
        this.title = title;
        this.msg = msg;
    }

}
