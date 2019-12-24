package com.xiaoban.utils;

import com.xiaoban.pojo.User;

import java.util.Properties;

import org.slf4j.Logger;

/**
 * @Description:
 * @Author: xiaoban
 * @Date: 2019/8/22 8:00 PM
 */
public class Config {
    //自己 默认key
    //http://seat.heuet.edu.cn:8091/yjxvalidate.aspx?Lib_UserOpenID=odsLhsuuYKLsmu0BInyXNjDul-Zc

    //    public static String SEAT_CHOOSE = SEAT_ROOT + "/kyApplyLog/Choose.aspx";
    // http://seat.heuet.edu.cn:8091/kyApplyLog/Seats.aspx
    // http://seat.heuet.edu.cn:8091/yjxvalidate.aspx?Lib_UserOpenID=odsLhsuuYKLsmu0BInyXNjDul-Zc  gytg3s552epsvm45ayjge555
    // http://seat.heuet.edu.cn:8091/yjxvalidate.aspx?Lib_UserOpenID=odsLhsitrU23swgiK6Bq4fwcngwc  gytg3s552epsvm45ayjge555
    // http://seat.heuet.edu.cn:8091/yjxvalidate.aspx?Lib_UserOpenID=odsLhsgITiKmHYXWZNb4jm8_70BM  gytg3s552epsvm45ayjge555
    //http://libapp.heuet.edu.cn:82/UserBinding/Validate.aspx?Lib_UserOpenID=odsLhsuuYKLsmu0BInyXNjDul-Zc
    //研究箱日期设定
    public static String SEAT_END_TIME = getStartTime();     //时间设定
    public static String KEY = "Yulan1225";      //管理员秘钥
    public static String OS = getOS();           //系统类型
    public static int BROWSER_TYPE = 1;         //浏览器类型
    public static Boolean IS_DEBUG = false;     //调试模式
    public static int SEAT_RUN_TIME = 120;      //运行秒数
    public static String DRIVER_DIR = "D:\\Chrome\\App\\";
    public static String USER_ROOT = "http://libapp.heuet.edu.cn:82";    //通用
    public static String SEAT_ROOT = "http://seat.heuet.edu.cn:8091";    //研究箱
    public static String SEAT_SEATS = SEAT_ROOT + "/kyApplyLog/Seats.aspx";
    public static String USER_INFO = USER_ROOT + "/UserBinding/userinfo.aspx";
    public static String SEAT_INDEX = SEAT_ROOT + "/kyApplyLog/button.aspx";
    public static String SEAT_LIST = SEAT_ROOT + "/kyApplyLog/List.aspx";
    public static String TIME_ERROR = Config.SEAT_ROOT + "/Timeerror.aspx";
    public static String BIND_ERROR = Config.SEAT_ROOT + "/binderror.aspx";
    public static String BESPEAKCANCEL = Config.USER_ROOT + "/Seat/BespeakCancel.aspx";

    public static MailInfo EMAIL = new MailInfo();

    public static String getMyDailySession(String key) {
        //http://libapp.heuet.edu.cn:82/Seat/Validate.aspx?Lib_UserOpenID=odsLhstE7K6mMF2fdkDcyqYUrBFc
        return USER_ROOT + "/Seat/Validate.aspx?Lib_UserOpenID=" + key;
    }

    public static String getMySeatSession(String key) {

        return SEAT_ROOT + "/yjxvalidate.aspx?Lib_UserOpenID=" + key;
    }

    public static String getMyInfoSession(String key) {
        return USER_ROOT + "/UserBinding/Validate.aspx?Lib_UserOpenID=" + key;
    }

    public static String getOS() {
        Properties props = System.getProperties();
        String os = props.getProperty("os.name");
        if ("Mac OS X".equals(os)) {
            os = "Mac";
        } else {
            if ("linux".equals(os.toLowerCase())) {
                os = "linux";
            } else {
                os = "Windows";
            }
        }
        return os;
    }

    public static String getStartTime() {
        String d = DateUtil.getNowDateStr("YYYY-MM-dd");
        String[] split = d.split("-");
        int year = Integer.parseInt(split[0]);
        int mon = Integer.parseInt(split[1]);
        int day = Integer.parseInt(split[2]);
        if (day > 1) {
            mon += 1;
            if (mon > 12) {
                mon += 1;
                year += 1;
            }
        }
        day = 1;
        String temp = year + "-" + mon + "-" + day + " 06:29:59";
        if (mon < 10) {
            temp = year + "-0" + mon + "-" + day + " 06:29:59";
        }
        return temp;
    }


}
