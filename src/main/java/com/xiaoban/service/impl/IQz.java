package com.xiaoban.service.impl;

import com.xiaoban.dao.LogDao;
import com.xiaoban.pojo.Log;
import com.xiaoban.pojo.User;
import com.xiaoban.service.LogService;
import com.xiaoban.utils.*;
import lombok.Data;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description: Qz抢座类实现
 * @Author: xiaoban
 * @Date: 2019/6/28 11:44 PM
 */

@Service
@Data
public class IQz implements Runnable {
    private LogService ls ;
    private String uuid;
    private XiaobanLog log;
    private User user;
    /*系统初始化*/
    public IQz(User user) {
        this.user = user;
        log = new XiaobanLog(user);
//        ld=SpringUtil.getBean(LogDao.class);
        uuid = log.getLogInfo().getUuid();
    }


    @Override
    public void run() {
        log.info("欢迎使用小班研究生系统!");
        //设置js
        log.info("[脚本]获得脚本成功!");
        WebDriver driver = QzUtils.getWebDriver();
        log.info("[时间]正在检查时间");
        QzUtils.seatIsOpenRoom(DateUtil.getNowDateStr());
        try {
            log.info("[请求]判断房间界面是否开放,请稍后！");
            while (!QzUtils.seatIsStart(driver,user)) Thread.sleep(20);
            while(!QzUtils.JoinRoom(driver,user)){
                Thread.sleep(20);
            }
            log.info("[开始]初始化全部完成,正在开始");
            QzStart qzStart = new QzStart(driver,user,log);
            new Thread(qzStart).start();
            while (qzStart.qzCode!=1){
                Thread.sleep(10000);
            }
            log.info("[完成]执行完毕!");

            driver.quit(); //关闭连接
        }catch (NoSuchSessionException e1){
            log.info("连接已关闭!------>"+e1.getCause());
        }
        catch (Exception e) {
            e.printStackTrace();
            log.info("run Error!------>"+e.getCause());
            if (Integer.parseInt(DateUtil.getNowDateStr("HH"))==6&&Integer.parseInt(DateUtil.getNowDateStr("mm"))==45){
                    driver.close(); //关闭连接
                    log.info("连接已关闭!");
            }else {
                MailInfo ema = Config.EMAIL;
                ema.setTitle("运行异常提示!");
                ema.setTo("1137188280@qq.com");
                ema.setMsg(e.getCause()+e.getMessage());
                SendMailSSL.sendEmil(ema);
                log.info("再试一次!");
                QzStart qzStart = new QzStart(driver,user,log);
                new Thread(qzStart).start();
            }
        }finally {
            Log log1 = new Log();
            log1.setCreated(new Date());
            log1.setName(user.getName());
            log1.setUuid(uuid);
            String a = "./xiaoban/"+DateUtil.getNowDateStr("YYYY-MM-dd")+"/qz-log.log";
            log1.setInfo(X.readFileS(a));
            if (ls!=null){
                ls.insert(log1);
            }else {
                System.out.println("注入失败!");
            }

        }
    }
    public IQz() {
    }
}
