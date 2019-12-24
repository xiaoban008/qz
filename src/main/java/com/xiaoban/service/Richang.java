package com.xiaoban.service;

import com.xiaoban.pojo.User;
import com.xiaoban.utils.Config;
import com.xiaoban.utils.DateUtil;
import com.xiaoban.utils.QzUtils;
import com.xiaoban.utils.XiaobanLog;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * @Description: 日常抢座代码
 * @Author: xiaoban
 * @Date: 2019/6/29 6:24 PM
 */
public class Richang implements Qz, Runnable {
    private XiaobanLog log ;
    private String uuid;
    public boolean tf = false;
    private int h = 20; //时
    private int m = 00; //分
    private int s = 30; //秒
    private int nowServer = h * 3600 + m * 60 + s;
    private String uid;
    private String url_session;
    private String url_user;
    private String url_userinfo = Config.USER_ROOT + "/UserBinding/userinfo.aspx";
    private String url_chonse = Config.USER_ROOT + "/Seat/BespeakSeat/BespeakChoice.aspx";
    private QzUtils qzUtils;
    private String js_seat;

    private User user;

    public Richang() {

    }
    public Richang(User user) {
        this.user = user;
        log = new XiaobanLog(user);
        uuid = log.getLogInfo().getUuid();
        url_session = Config.getMyDailySession(user.getKey());
        getJs_seat();
    }
    @Override
    public void run() {
        log.info("欢迎使用小班快速预约系统!");
        // 设置禁止加载项
        log.info("设置脚本成功!");
        WebDriver driver = null;
        driver = getWebDriver();
        try {
            Thread.sleep(getSleep());
        } catch (InterruptedException e) {
            log.info("睡眠异常!");
        }
        try {
            log.info("初始化成功!");
            start(driver);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("run's Error!");
        } finally {
            driver.close(); //关闭连接
            log.info("连接已关闭!");
        }
    }
    @Override
    public void getJs_seat() {
        js_seat = "setInterval(\n" +
                "    function () {\n" +
                "        var qr=document.getElementsByClassName('confirm');\n" +
                "        qr[0].click(); \n" +
                "        var xz=document.getElementsByTagName('a');\n" +
                "        var myDate = new Date();\n" +
                "        var h = myDate.getHours();\n" +
                "        var m = myDate.getMinutes(); \n" +
                "        var s = myDate.getSeconds();\n" +
                "        var xx = h*3600+m*60+s;//81030\n" +
                "        if (xx>=" + nowServer + ") {\n" +
                "          xz[0].click();\n" +
                "          console.log(s);\n" +
                "        }    \n" +
                "        },100);";
    }
    @Override
    public WebDriver getWebDriver() {
        return QzUtils.getWebDriver();
    }

    @Override
    public boolean isStart(WebDriver driver) throws Exception {
        return true;
    }
    @Override
    public void start(WebDriver driver) throws Exception {
        driver.get(url_session);
        driver.get(url_chonse);
        if (!(url_chonse.equals(driver.getCurrentUrl()))) {
            //进来验证一波
            start(driver);
        }
        m = Integer.parseInt(DateUtil.getNowDateStr("mm"));
        if (url_chonse.equals(driver.getCurrentUrl())) {
            log.info("[当前位置->快速预约页面]");
            log.info("开始干活中！" + DateUtil.getNowDateStr());
            ((JavascriptExecutor) driver).executeScript(js_seat);   //执行抢座

            for (int i = m; Integer.parseInt(DateUtil.getNowDateStr("mm"))-m < 1; ) {
                log.info("正在抢座----->[" + user.getName() + "]");
                Thread.sleep(5000);
            }
            driver.get(url_session);
            qzUtils.isSuccess(driver, 1);
        } else {
            if (url_chonse.equals(driver.getCurrentUrl())) {         //失败判断
                log.info("[房间判断]没开始!...");
            } else {
                start(driver);
            }
        }
    }
    private int getSleep() {
        String now = DateUtil.getNowDateStr("HH:mm:ss");
        int h = Integer.parseInt(now.split(":")[0]);
        int m = Integer.parseInt(now.split(":")[1]);
        int s = Integer.parseInt(now.split(":")[2]);
        int now1 = h * 3600 + m * 60 + s;

        if (now1 >= nowServer) {
            log.info("当前时间允许");
            return 0;
        } else {
            int sleep = nowServer - now1;
            h = sleep / 3600;
            m = sleep % 3600 / 60;
            s = sleep % 60;
//            System.out.println("程序将在:" + h + "小时" + m + "分" + s + "秒之后继续执行!");
            String tmp = "程序将在:" + h + "小时" + m + "分" + s + "秒之后继续执行!";
            log.info(tmp);
            return sleep * 1000;
        }
    }
    public boolean isTf() {
        return tf;
    }

    public void setTf(boolean tf) {
        this.tf = tf;
    }

    public int getNowServer() {
        return nowServer;
    }

    public void setNowServer(int nowServer) {
        this.nowServer = nowServer;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUrl_session() {
        return url_session;
    }

    public void setUrl_session(String url_session) {
        this.url_session = url_session;
    }

    public String getUrl_user() {
        return url_user;
    }

    public void setUrl_user(String url_user) {
        this.url_user = url_user;
    }

    public String getUrl_userinfo() {
        return url_userinfo;
    }

    public void setUrl_userinfo(String url_userinfo) {
        this.url_userinfo = url_userinfo;
    }

    public String getUrl_chonse() {
        return url_chonse;
    }

    public void setUrl_chonse(String url_chonse) {
        this.url_chonse = url_chonse;
    }

    public QzUtils getQzUtils() {
        return qzUtils;
    }

    public void setQzUtils(QzUtils qzUtils) {
        this.qzUtils = qzUtils;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
