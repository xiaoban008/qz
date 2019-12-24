package com.xiaoban;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Properties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
* 入口函数
* */
@SpringBootApplication
@MapperScan(basePackages = "com.xiaoban.dao")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
