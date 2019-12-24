package com.xiaoban.utils;

import com.xiaoban.pojo.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import lombok.Data;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

/**
 * @Description:
 * @Author: xiaoban
 * @Date: 2019/6/29 3:59 PM
 */
@Data
public class QzUtils {

    public static XiaobanLog log = new XiaobanLog(new User("system"));

    /**
     * @param driver 驱动
     * @Description: 查询研究箱结果
     */
    public static void isSuccess(WebDriver driver, User user) {
        MailInfo email = new MailInfo();
        log.info("正在查询结果", true);
        log.info("正在查询座位信息:", false);
        String sucText = "";
        String monthNow = "0";
        String date1 = "";
        String s = "";
        try {
            driver.get(Config.SEAT_LIST);
            sucText = driver.findElement(By.id("div4")).getText();
            monthNow = DateUtil.getNowDateStr("MM");
            date1 = driver.findElement(By.id("div4")).findElement(By.tagName("input")).getAttribute("value");
            s = String.valueOf((date1.split(":", 2)[1].charAt(5)));
        } catch (NoSuchElementException e) {
            log.info("元素抓取失败,可能无座位!");
        } catch (StaleElementReferenceException e1) {
            log.info("StaleElementReferenceException！");
        } catch (TimeoutException e) {
            log.info("请求超时!");
        }
        String monthSelect;
        if (monthNow.equals("10") || monthNow.equals("11") || monthNow.equals("12")) {
            char b = date1.split(":", 2)[1].charAt(6);
            monthSelect = s + b;
        } else {
            monthSelect = "0" + s;
        }
        // 获取个人资料
        String emailQq;
        User user1 = getUserInfo(driver, user);
        if (user1 != null) {
            user.setQq(user1.getQq());
            user.setTel(user1.getTel());
            user.setSex(user1.getSex());
            user.setSid(user1.getSid());
            user.setName(user1.getName());
        }
        emailQq = user.getQq();
        if (emailQq == null || "".equals(emailQq)) {
            emailQq = "1137188280";
        }
        email.setTo(emailQq + "@qq.com");
        log.info("用户信息:[姓名]" + user.getName() + "  [学号]" + user.getSid() + "  [QQ]" + user.getQq(), false);
        date1 = DateUtil.getNowDateStr();
        if (monthNow.equals(monthSelect)) {
            //月份与当前相同
            if (sucText != null && !("".equals(sucText))) {
                log.info("本月抢到座位！", true);
                log.info("房间信息:", false);
                email.setTitle("[" + user.getName() + "]研究生抢座提示");
                email.setMsg(
                        "亲爱的[" + user.getName() + "]同学:<br />您的座位信息如下<br />" + "房间信息:" + sucText + "<br />[当前时间]  " + date1);
                log.info(sucText.split("\n")[0], false);
                log.info(sucText.split("\n")[2], false);
                if (SendMailSSL.sendEmil(email)) {
                    log.info("邮件通知成功!", true);
                } else {
                    log.info("邮件通知失败!", true);
                }
            }
        } else {
            log.info("本月没有座位！", true);
            email.setTitle("[" + user.getName() + "]小班研究生抢座提示");
            String msg = "亲爱的[" + user.getName() + "]同学:<br />本月抢座已运行完毕,但很遗憾貌似查询没有您的座位信息!";
            email.setMsg(msg + "<br />[当前时间]  " + date1);
//            if (SendMailSSL.sendEmil(email)) {
//                log.info("邮件通知成功!", true);
//            } else {
//                log.info("邮件通知失败!", true);
//            }
            email.setTo("1137188280@qq.com");
//            email.setMsg(user.toString() + "<br />" + "好像失败了，具体请查看!");
//            email.setTitle("[" + user.getName() + "]抢座好像失败了!");
            if (SendMailSSL.sendEmil(email)) {
                log.info("邮件通知成功!", true);
            } else {
                log.info("邮件通知失败!", true);
            }
        }
        String img = Config.USER_ROOT + "/UserBinding/loadimg.aspx?readerno=" + user.getSid();
        DownUtil.Down(img, user.getName());
        //资料结束
    }

    public void isSuccess(WebDriver driver, int i) {
//        log.info("正在查询日常结果", true);
//        try {
//            driver.get(url_seat);
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            log.info("[睡眠异常]->isSuccess");
//        } catch (TimeoutException e) {
//            log.info("请求个人资料页超时!");
//        }
//        log.info("正在查询座位信息:", false);
//        String zuowei = "无";
//        String RoomName = "无";
//        String BeginTime = "开始时间为空！";
//        try {
//            zuowei = driver.findElement(By.id("lblSeatNo")).getAttribute("value");
//            RoomName = driver.findElement(By.id("lblRoomName")).getAttribute("value");
//            BeginTime = driver.findElement(By.id("lblBeginTime")).getAttribute("value");
//        } catch (Exception e) {
//            log.info("获取资料异常!");
//        }
//        // 获取个人资料
//        user.setKey(uid);
//        emailQq = user.getQq();
//        if (emailQq == null) {
//            emailQq = "1137188280";
//        }
//        email.setTo(emailQq + "@qq.com");
//        log.info("用户信息:[姓名]" + user.getName() + "  [学号]" + user.getSid() + "  [QQ]" + user.getQq(), false);
//        if (RoomName != null && !("".equals(RoomName))) {
//            //月份与当前相同
//            if (zuowei != null && !("".equals(zuowei))) {
//                log.info("抢到座位！", true);
//                log.info("房间信息:", false);
//                log.info(RoomName + "\t\t" + zuowei, false);
//                log.info("选座时间" + BeginTime, false);
//                email.setTitle("小班抢座成功通知[" + user.getName() + "]");
//                email.setMsg("[" + user.getName() + "]同学:<br />" + RoomName + zuowei + "<br />" + BeginTime);
//                if (SendMailSSL.sendEmil(email)) {
//                    log.info("邮件通知成功!", true);
//                } else {
//                    log.info("邮件通知失败!", true);
//                }
//            }
//        } else {
//            RoomName = "您的喜爱座位已经被人抢先一步抢走了！";
//            zuowei = "下次再试试吧!";
//            email.setTitle("小班抢座通知[" + user.getName() + "]");
//            email.setMsg("[" + user.getName() + "]同学:<br />" + RoomName + zuowei + "<br>" + DateUtil.getNowDateStr());
//            if (SendMailSSL.sendEmil(email)) {
//                log.info("邮件通知成功!", true);
//            } else {
//                log.info("邮件通知失败!", true);
//            }
//            log.info("没有约到座位！", true);
//        }
//        String img = "http://libapp.heuet.edu.cn:82/UserBinding/loadimg.aspx?readerno=" + user.getSid();
//        DownUtil.Down(img, user.getName());
        //资料结束
    }

    public static User getUserInfo(WebDriver driver, User user) {
        try {
            driver.get(Config.getMyInfoSession(user.getKey()));
            driver.get(Config.USER_INFO);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.info("[睡眠异常]->isSuccess");
        } catch (TimeoutException e) {
            log.info("[请求超时]->isSuccess");
        }
        try {
            user.setSid(driver.findElement(By.id("txtIdent_id")).getAttribute("value"));
            user.setName(driver.findElement(By.id("txtReaderName")).getAttribute("value"));
            user.setTel(driver.findElement(By.id("txtReaderTel")).getAttribute("value"));

        } catch (Exception e) {
            log.info("获取普通资料 异常!");
        }
//        user.uuid = driver.findElement(By.id("txtNationalID")).getAttribute("value");
        if (user.getQq() == null || "".equals(user.getQq())) {
            user.setQq(driver.findElement(By.id("txtReaderQQ")).getAttribute("value"));
        }
        return user;
    }

    public static WebDriver getWebDriver() {
        WebDriver w;
        if (Config.BROWSER_TYPE == 1) {
            //火狐浏览器
           log.info(Config.OS);
            if (!"Mac OS X".equals(Config.OS)&&!"mac".equals(Config.OS.toLowerCase())&&!"linux".equals(Config.OS.toLowerCase())) {
                System.setProperty("webdriver.gecko.driver", "D:\\geckodriver.exe");
            } else {
                System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
            }
            System.setProperty("webdriver.firefox.logfile", "/dev/null");
            FirefoxOptions options = new FirefoxOptions();
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("permissions.default.image", 2);
            profile.setPreference("permissions.default.stylesheet", 2);
            profile.setPreference("browser.migration.version", 9001);
            // 设置禁止加载项
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            if (!Config.IS_DEBUG) {
                options.addArguments("--headless");
            }
            options.setProfile(profile);
            w = new FirefoxDriver(options);
//            w = new FirefoxDriver();
        } else if (Config.BROWSER_TYPE == 2) {
            //谷歌浏览器
            ChromeOptions options = new ChromeOptions();
            options.addArguments("blink-settings=imagesEnabled=false");
            if (!"Mac OS X".equals(Config.OS) && !"Mac".equals(Config.OS)) {
                System.setProperty("webdriver.chrome.driver", "D:\\Chrome\\App\\chromedriver.exe");//设置驱动的路径
                options.setBinary("D:\\Chrome\\App\\chrome.exe");
            } else {
                System.out.println("MAC系列");
                System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");//设置驱动的路径
            }
            if (!Config.IS_DEBUG) {
                options.addArguments("--headless");
            }
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("log-level=4");
            w = new ChromeDriver(options);

        } else {
            SafariOptions options = new SafariOptions();
            w = new SafariDriver(options);
        }
        w.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);//一分钟超时
        w.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);//一分钟超时
        return w;
    }

    public static void executeJs(WebDriver driver, String js) {
        log.info("执行脚本代码中");
        try {
            ((JavascriptExecutor) driver).executeScript(js);
        } catch (Exception e) {
            log.warn("执行js异常!", true);
        }
    }

    public static int seatIsOpenRoom(String nowTime) {
        log.info("检测时间是否开放中!");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        log.info(nowTime);
        Date sd1 = null;
        Date sd2 = null;
        try {
            sd1 = df.parse(nowTime);
            sd2 = df.parse(Config.SEAT_END_TIME);
            long sleppTime = (sd2.getTime() - sd1.getTime()) > 0 ? (sd2.getTime() - sd1.getTime()) : 1000;
            int va = sd1.compareTo(sd2);
            if (va == 0) {
                log.info("房间现在开放了", true);
                return 0;
            } else if (va > 0) {
                log.info("房间已经开放了", true);
                try {
                    Thread.sleep(sleppTime);
                } catch (InterruptedException e) {
                    log.warn("sleep异常", true);
                }
                return 1;
            } else {
                log.info("房间将在" + sleppTime / (1000 * 3600) + "小时" + ((sleppTime / 1000) % 3600) / 60 + "分"
                        + (sleppTime / 1000) % 60 + "秒后开放!", true);
                try {
                    sd1 = df.parse(DateUtil.getNowDateStr());
                    sleppTime = (sd2.getTime() - sd1.getTime()) > 0 ? (sd2.getTime() - sd1.getTime()) : 1000;
                    Thread.sleep(sleppTime);
                } catch (InterruptedException e) {
                    log.warn("sleep异常", true);
                }
                return -1;
            }
        } catch (ParseException e) {
            log.info("[程序异常->isOpenRoom]", true);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                log.warn("sleep异常", true);
            }
            return -1;
        }
    }

    public static boolean seatIsStart(WebDriver w, User user) {
        //先请求一波session
        try {
            int temp = 0;
            while (!getSeatSession(w, Config.getMySeatSession(user.getKey()))) {
                temp++;
            }
            //进入房间选择
            log.info("房间已开放!"+temp);
            return true;
        } catch (Exception e) {
            log.info("判断房间是否开放出错!");
            return false;
        }
    }

    public static String getJs_seat(int roomid, int seatid) {
        String tmp_room;
        String roomName;
        switch (roomid) {
            case 1:
                tmp_room = "00000001-1";
                roomName = "[岳麓]";
                break;
            case 2:
                tmp_room = "00000001-2";
                roomName = "[白露]";
                break;
            case 3:
                tmp_room = "00000001-3";
                roomName = "[石鼓]";
                break;
            default:
                tmp_room = "00000002-2";
                roomName = "[应天]";
                break;
        }
        if (seatid < 0 || seatid >= 23) {
            seatid = 1;
        }
        //拼接JS
        String js = "function zh(fangjianhao) {\n"
                + "    var fjh = \"\";\n"
                + "    if (fangjianhao <= 9) {\n"
                + "        fjh = '0' + fangjianhao;\n"
                + "    } else {\n"
                + "        fjh = '' + fangjianhao;\n"
                + "    }\n"
                + "    return fjh;\n"
                + "}\n"
                + "\n"
                + "function confirm(obj, roomno) {\n"
                + "    yywebDesign.YanjiuX.Seats.sessionValue(obj, roomno);\n"
                + "}\n"
                + "\n"
                + "function start() {\n"
                + "    var count = 1;\n"
                + "    setInterval(function () {\n"
                + "            if (count <= 8) {\n"
                + "                fnum = " + seatid + ";\n"
                + "                cnums = '" + tmp_room + "';\n"
                + "            } else {\n"
                + "                if (count <= 11) {\n"
                + "                    fnum = Math.ceil(Math.random() * 10);\n"
                + "                } else {\n"
                + "                    fnum = Math.ceil(Math.random() * 22);\n"
                + "                }\n"
                + "                cnum = Math.ceil(Math.random() * 4);\n"
                + "                switch (cnum) {\n"
                + "                    case 1:\n"
                + "                        cnums = '00000001-1';\n"
                + "                        break;\n"
                + "                    case 2:\n"
                + "                        cnums = '00000001-2';\n"
                + "                        break;\n"
                + "                    case 3:\n"
                + "                        cnums = '00000001-3';\n"
                + "                        break;\n"
                + "                    default:\n"
                + "                        cnums = '00000002-2';\n"
                + "                        break;\n"
                + "                }\n"
                + "            }\n"
                + "            confirm(zh(fnum), cnums);\n"
                + "            "
                + "            console.log(count++);\n"
                + "            if (count === 23) {\n"
                + "                count = 1;\n"
                + "            }\n"
                + "        }\n"
                + "        , 20);\n"
                + "}\n"
                + "start();\n"
                + "start();\n"
                + "start();";
        log.info("小班将优先为您抢" + roomName + "的" + seatid + "号座位!");
        return js;
    }

    public static String getJoinRoom(int roomid) {
        String room;
        switch (roomid) {
            case 1:
                room = "00000001-1";
                break;
            case 2:
                room = "00000001-2";
                break;
            case 3:
                room = "00000001-3";
                break;
            default:
                room = "00000002-2";
                break;
        }
        return "var ab = function () {};\n"
                + "jQuery.extend(ab.prototype,\n"
                + "    jQuery.extend(new AjaxPro.AjaxClass(), {\n"
                + "        sessionValue: function (roomno) {\n"
                + "            return this.invoke(\"sessionValue\", {\"roomno\": roomno},\n"
                + "                null);\n"
                + "        }\n"
                + "        ,url: '/ajaxpro/YuYue.Web.kyApplyLog.Choose,YuYue.Web.ashx'\n"
                + "    }));\n"
                + "var a = new ab();\n"
                + "a.sessionValue('" + room + "');\n"
                + "window.location.reload();";
    }

    private static boolean getSeatSession(WebDriver w, String url_session) {
        try {
            w.get(url_session);
            if (Config.SEAT_INDEX.equals(w.getCurrentUrl())) {
                return true;
            } else {
                if ((Config.TIME_ERROR).equals(w.getCurrentUrl())) {
                    log.info("当前位置->#时间错误页#");
                }
                if (Config.BIND_ERROR.equals(w.getCurrentUrl())) {
                    log.info("当前位置->#尚未绑定页#");
                    w.quit();
                    return true;
                }
                return false;
            }
        } catch (WebDriverException e) {
            log.info("网页未开放!");
            return false;
        } catch (Exception e) {
            log.info("请求登录失败!");
            return false;
        }
    }

    public static boolean JoinRoom(WebDriver w, User user) {
        try {
            w.get(Config.SEAT_SEATS);
            if (Config.SEAT_SEATS.equals(w.getCurrentUrl())) {
                executeJs(w, QzUtils.getJoinRoom(user.getRoomId()));
                return true;
            } else {
                if (Config.TIME_ERROR.equals(w.getCurrentUrl())) {
                    log.info("早6点到晚23点预约!");
                } else {
                    log.info("进入研究箱失败!");
                }
                return false;
            }
        } catch (NoSuchSessionException e1) {
            log.info("异常---->连接已关闭!");
            return true;
        } catch (Exception e) {
            log.info("异常---->进入研究箱失败!");
            try {
                if (!"".equals(w.findElement(By.className("mainbox")).getText())) {
                    return true;
                }
            } catch (Exception e1) {
            }
            return false;
        }
    }

    //    public static void addCookie(WebDriver w){
//        Cookie c1 = new Cookie("ASP.NET_SessionId", "vw0qw345qdtsuajkejw0243u", "/");
//        Cookie c2 = new Cookie("wxUser", "odsLhsriZgkIv6UkIE-y_T_BAR2I", "/");
//        Set<Cookie> cookies1 = new HashSet<>();
//        cookies1.add(c1);
//        cookies1.add(c2);
//        for (Cookie c : cookies1) {
//            w.manage().addCookie(c);
//        }
//        w.navigate().refresh();
//    }
    public static String setAsync() {
        return "AjaxPro.Request.prototype = {\n"
                + "    url: null,\n"
                + "    callback: null,\n"
                + "    onLoading: AjaxPro.noOperation,\n"
                + "    onError: AjaxPro.noOperation,\n"
                + "    onTimeout: AjaxPro.noOperation,\n"
                + "    onStateChanged: AjaxPro.noOperation,\n"
                + "    args: null,\n"
                + "    context: null,\n"
                + "    isRunning: false,\n"
                + "    abort: function() {\n"
                + "        if(this.timeoutTimer != null) {\n"
                + "            clearTimeout(this.timeoutTimer);\n"
                + "        }\n"
                + "        if(this.xmlHttp) {\n"
                + "            this.xmlHttp.onreadystatechange = AjaxPro.noOperation;\n"
                + "            this.xmlHttp.abort();\n"
                + "        }\n"
                + "        if(this.isRunning) {\n"
                + "            this.isRunning = false;\n"
                + "            this.onLoading(false);\n"
                + "        }\n"
                + "    },\n"
                + "    dispose: function() {\n"
                + "        this.abort();\n"
                + "    },\n"
                + "    getEmptyRes: function() {\n"
                + "        return {\n"
                + "            error: null,\n"
                + "            value: null,\n"
                + "            request: {method:this.method, args:this.args},\n"
                + "            context: this.context,\n"
                + "            duration: this.duration\n"
                + "        };\n"
                + "    },\n"
                + "    endRequest: function(res) {\n"
                + "        this.abort();\n"
                + "        if(res.error != null) {\n"
                + "            this.onError(res.error, this);\n"
                + "        }\n"
                + "        if(typeof this.callback == \"function\") {\n"
                + "            this.callback(res, this);\n"
                + "        }\n"
                + "    },\n"
                + "    mozerror: function() {\n"
                + "        if(this.timeoutTimer != null) {\n"
                + "            clearTimeout(this.timeoutTimer);\n"
                + "        }\n"
                + "        var res = this.getEmptyRes();\n"
                + "        res.error = {Message:\"Unknown\",Type:\"ConnectFailure\",Status:0};\n"
                + "        this.endRequest(res);\n"
                + "    },\n"
                + "    doStateChange: function() {\n"
                + "        this.onStateChanged(this.xmlHttp.readyState, this);\n"
                + "        if(this.xmlHttp.readyState != 4 || !this.isRunning) {\n"
                + "            return;\n"
                + "        }\n"
                + "        this.duration = new Date().getTime() - this.__start;\n"
                + "        if(this.timeoutTimer != null) {\n"
                + "            clearTimeout(this.timeoutTimer);\n"
                + "        }\n"
                + "        var res = this.getEmptyRes();\n"
                + "        if(this.xmlHttp.status == 200 && this.xmlHttp.statusText == \"OK\") {\n"
                + "            res = this.createResponse(res);\n"
                + "        } else {\n"
                + "            res = this.createResponse(res, true);\n"
                + "            res.error = {Message:this.xmlHttp.statusText,Type:\"ConnectFailure\",Status:this.xmlHttp.status};\n"
                + "        }\n"
                + "        this.endRequest(res);\n"
                + "    },\n"
                + "    createResponse: function(r, noContent) {\n"
                + "        if(!noContent) {\n"
                + "            var responseText = \"\" + this.xmlHttp.responseText;\n"
                + "            if(AjaxPro.cryptProvider != null && typeof AjaxPro.cryptProvider == \"function\") {\n"
                + "                responseText = AjaxPro.cryptProvider.decrypt(responseText);\n"
                + "            }\n"
                + "            if(this.xmlHttp.getResponseHeader(\"Content-Type\") == \"text/xml\") {\n"
                + "                r.value = this.xmlHttp.responseXML;\n"
                + "            } else {\n"
                + "                if(responseText != null && responseText.trim().length > 0) {\n"
                + "                    r.json = responseText;\n"
                + "                    eval(\"r.value = \" + responseText + \"*\" + \"/\");\n"
                + "                }\n"
                + "            }\n"
                + "        }\n"
                + "        return r;\n"
                + "    },\n"
                + "    timeout: function() {\n"
                + "        this.duration = new Date().getTime() - this.__start;\n"
                + "        var r = this.onTimeout(this.duration, this);\n"
                + "        if(typeof r == \"undefined\" || r != false) {\n"
                + "            this.abort();\n"
                + "        } else {\n"
                + "            this.timeoutTimer = setTimeout(this.timeout.bind(this), AjaxPro.timeoutPeriod);\n"
                + "        }\n"
                + "    },\n"
                + "    invoke: function(method, args, callback, context) {\n"
                + "        this.__start = new Date().getTime();\n"
                + "        if(this.xmlHttp == null) {\n"
                + "            this.xmlHttp = new XMLHttpRequest();\n"
                + "        }\n"
                + "        this.isRunning = true;\n"
                + "        this.method = method;\n"
                + "        this.args = args;\n"
                + "        this.callback = callback;\n"
                + "        this.context = context;\n"
                + "        var async = typeof(callback) == \"function\" && callback != AjaxPro.noOperation;\n"
                + "        if(async) {\n"
                + "            if(MS.Browser.isIE) {\n"
                + "                this.xmlHttp.onreadystatechange = this.doStateChange.bind(this);\n"
                + "            } else {\n"
                + "                this.xmlHttp.onload = this.doStateChange.bind(this);\n"
                + "                this.xmlHttp.onerror = this.mozerror.bind(this);\n"
                + "            }\n"
                + "            this.onLoading(true);\n"
                + "        }\n"
                + "\n"
                + "        var json = AjaxPro.toJSON(args) + \"\";\n"
                + "        if(AjaxPro.cryptProvider != null) {\n"
                + "            json = AjaxPro.cryptProvider.encrypt(json);\n"
                + "        }\n"
                + "\n"
                + "        this.xmlHttp.open(\"POST\", this.url, true);\n"
                + "        this.xmlHttp.setRequestHeader(\"Content-Type\", \"text/plain; charset=utf-8\");\n"
                + "        this.xmlHttp.setRequestHeader(\"X-\" + AjaxPro.ID + \"-Method\", method);\n"
                + "\n"
                + "        if(AjaxPro.token != null && AjaxPro.token.length > 0) {\n"
                + "            this.xmlHttp.setRequestHeader(\"X-\" + AjaxPro.ID + \"-Token\", AjaxPro.token);\n"
                + "        }\n"
                + "\n"
                + "        if(!MS.Browser.isIE) {\n"
                + "            this.xmlHttp.setRequestHeader(\"Connection\", \"close\");\t\t// Mozilla Bug #246651\n"
                + "        }\n"
                + "        this.timeoutTimer = setTimeout(this.timeout.bind(this), AjaxPro.timeoutPeriod);\n"
                + "        try{ this.xmlHttp.send(json); }catch(e){}\t// IE offline exception\n"
                + "        if(!async) {\n"
                + "            return this.createResponse({error: null,value: null});\n"
                + "        }\n"
                + "        return true;\n"
                + "    }\n"
                + "};";

    }

    public static String getAddSession(int roomid, int seatid) {
        String tmp_room;
        String roomName;
        switch (roomid) {
            case 1:
                tmp_room = "00000001-1";
                roomName = "[岳麓]";
                break;
            case 2:
                tmp_room = "00000001-2";
                roomName = "[白露]";
                break;
            case 3:
                tmp_room = "00000001-3";
                roomName = "[石鼓]";
                break;
            default:
                tmp_room = "00000002-2";
                roomName = " [应天]";
                break;
        }
        if (seatid < 0 || seatid >= 23) {
            seatid = 1;
        }
        log.info("roomName:"+roomName);
        //拼接JS
        String js ="function zh(fangjianhao) {\n" +
                "    var fjh = \"\";\n" +
                "    if (fangjianhao <= 9) {\n" +
                "        fjh = '0' + fangjianhao;\n" +
                "    } else {\n" +
                "        fjh = '' + fangjianhao;\n" +
                "    }\n" +
                "    return fjh;\n" +
                "}\n" +
                "\n" +
                "function confirm(obj, roomno) {\n" +
                "    yywebDesign.YanjiuX.Seats.sessionValue(obj, roomno);\n" +
                "}\n" +
                "\n" +
                "var count = 1;\n" +
                "var a = 1;\n" +
                "setInterval(function () {\n" +
                "        var fnum = " + seatid + ";\n" +
                "        var cnum = 1;\n" +
                "        var cnums='" + tmp_room + "';\n" +
                "        if (count <= 4) {\n" +
                "            fnum = 7;\n" +
                "            cnums = '00000001-1';\n" +
                "        } else {\n" +
                "            fnum = Math.ceil(Math.random() * 22);\n" +
                "            cnum = Math.ceil(Math.random() * 4);\n" +
                "            switch (cnum) {\n" +
                "                case 1:\n" +
                "                    cnums = '00000001-1';\n" +
                "                    break;\n" +
                "                case 2:\n" +
                "                    cnums = '00000001-2';\n" +
                "                    break;\n" +
                "                case 3:\n" +
                "                    cnums = '00000001-3';\n" +
                "                    break;\n" +
                "                default:\n" +
                "                    cnums = '00000002-2';\n" +
                "                    break;\n" +
                "            }\n" +
                "        }\n" +
                "        if (a ===1) {\n" +
                "            confirm(zh(fnum), cnums);\n" +
                "            a++;\n" +
                "        }\n" +
                "        $.get('http://seat.heuet.edu.cn:8091/kyApplyLog/add.aspx', '', function () {\n" +
                "            confirm(zh(fnum), cnums);\n" +
                "        });\n" +
                "        count++;\n" +
                "        if (count === 23) {\n" +
                "            count = 1;\n" +
                "        }\n" +
                "    }\n" +
                "    , 40);";
        return js;
    }

}
