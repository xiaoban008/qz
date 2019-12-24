package com.xiaoban.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @描述 日期工具类
 */
public class DateUtil {
    public static String  getNowDateStr(){
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
    public static String  getNowDateStr(String pattern){
        Date date = new Date();
        DateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static Date getNowDate() {
        return new Date();
    }
    public static String  DateToStr(Date date){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
