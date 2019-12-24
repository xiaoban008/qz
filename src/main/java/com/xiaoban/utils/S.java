package com.xiaoban.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 系统配置
 * @Author: xiaoban
 * @Date: 2019/9/1 4:25 PM
 */
@Slf4j
public class S {
    public static void start() {
        String chromeWin = "http://my.zp68.com/filestores/2019/09/24/2e48be12eebabea076541aee05f5649e.exe";
        String driver = "http://my.zp68.com/filestores/2019/09/24/100ec6c24a1b51fb624fba4724593792.exe";
        String path = Config.DRIVER_DIR;
        File file = new File(path+ File.separator+"Chrome.exe");
        file = file.getParentFile();
        if (!file.exists()){
            boolean mkdir = file.mkdirs();
            if (mkdir)log.info("创建文件夹成功!");
        }
        log.info("配置驱动中...");
        String fileName = "chromedriver.exe";
        boolean f;
         file = new File(path + File.separator + fileName);
        if (file.exists()) {
            f = true;
            log.info("文件已经存在!");
        } else {
            f = DownUtil.downLoadFromUrl(driver, fileName, path);
            if (f) {
                log.info("驱动文件配置完毕");
            }else {
                log.info("驱动配置失败,请手动下载"+driver);
            }
        }
        fileName = "Chrome.exe";
        file = new File(path + File.separator + fileName);
        if (file.exists()) {
            f = true;
            log.info("浏览器已经安装!");
        } else {
            fileName = "xiaoban.exe";
            file = new File(path + File.separator + fileName);
            if (file.exists()) {
                f = true;
                log.info("安装包已下载!");
            }else {
                f = DownUtil.downLoadFromUrl(chromeWin, fileName, path);
            }
        }
        if (f) {
            log.info("配置文件下载完毕,请手动安装!");
            X.openExe(path + File.separator + fileName);
        }else {
            log.info("驱动配置失败,请手动下载"+driver);
        }
    }
}
