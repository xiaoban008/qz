package com.xiaoban.utils;

import com.xiaoban.pojo.Log;
import com.xiaoban.pojo.User;
import com.xiaoban.service.LogService;
import java.util.Date;
import java.util.UUID;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Description: 小班日志类
 * @Author: xiaoban
 * @Date: 2019/8/23 6:53 PM
 */
@Data
public class XiaobanLog {
    private  Logger log = LoggerFactory.getLogger(XiaobanLog.class);
    private  Log logInfo;
    public XiaobanLog(User u){
        logInfo =new Log();
        logInfo.setName(u.getName());
        logInfo.setCreated(new Date());
        logInfo.setUuid(UUID.randomUUID().toString());
    }
    public void info(String info){
        log.info("["+logInfo.getName()+"]"+info);
    }
    public void info(String s, boolean b) {
        info("["+logInfo.getName()+"]"+s);
    }
    public void warn(String info, boolean b) {
        log.warn(info);
    }
}
