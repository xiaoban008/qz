package com.xiaoban.utils;

import com.xiaoban.pojo.User;
import lombok.Data;
import org.openqa.selenium.WebDriver;

/**
 * @Description:
 * @Author: xiaoban
 * @Date: 2019/9/1 9:38 AM
 */
@Data
public class IsSuccess implements Runnable {
    private User u;
    private WebDriver driver = QzUtils.getWebDriver();
    @Override
    public void run() {
        System.out.println("查询"+u.getName()+"中");
        driver.get(Config.getMySeatSession(u.getKey()));
        QzUtils.isSuccess(driver, u);
        driver.quit();
    }
}
