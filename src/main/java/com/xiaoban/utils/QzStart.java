package com.xiaoban.utils;

import com.xiaoban.pojo.User;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @Description:
 * @Author: xiaoban
 * @Date: 2019/8/26 4:25 PM
 */
public class QzStart implements Runnable {
    public int qzCode = 0;
    private WebDriver driver;
    private XiaobanLog log;
    private User user;

    public QzStart(WebDriver driver, User user, XiaobanLog log) {
        this.driver = driver;
        this.log = log;
        this.user = user;
    }


    @Override
    public void run() {
        start();
    }

    public void start() {
        try {
            int s_start = Integer.parseInt(DateUtil.getNowDateStr("mm")) * 60 + Integer.parseInt(DateUtil.getNowDateStr("ss"));
            if (Config.SEAT_SEATS.equals(driver.getCurrentUrl())) {
                log.info("[位置]->[研究箱内]", true);
                log.info("[核心]正在努力为您抢座中！", true);
                QzUtils.executeJs(driver, QzUtils.setAsync());
                //异步设置
                QzUtils.executeJs(driver, QzUtils.getAddSession(user.getRoomId(), user.getSeatId()));
                // QzUtils.executeJs(driver,js_seat);
                while ((Integer.parseInt(DateUtil.getNowDateStr("mm")) * 60 + Integer.parseInt(DateUtil.getNowDateStr("ss"))) - s_start < Config.SEAT_RUN_TIME) {
                    qzCode = 0;
                }
                //成功查询
                qzCode = 1;
            } else {
                log.info("进入[研究箱]内失败!");
            }
        } catch (NoSuchSessionException e) {
            log.info("执行出错!");
            qzCode = 1;
        }
    }
}
