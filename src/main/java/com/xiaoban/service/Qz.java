package com.xiaoban.service;
import org.openqa.selenium.WebDriver;
/*小班抢座接口*/

public interface Qz {
    void run() throws Exception;
    void getJs_seat();
    WebDriver getWebDriver();
    boolean isStart(WebDriver w) throws Exception;
    void start(WebDriver driver) throws Exception;
}

