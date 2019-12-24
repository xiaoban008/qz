package com.xiaoban.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @Description:文件操作相关
 * @Author: xiaoban
 * @Date: 2019/7/1 4:12 PM
 */
@Slf4j
public class FileUtile {
    public static String readFile(String pathname) {
        try (FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
        ) {
            String line;
            //网友推荐更加简洁的写法
            int a =0;
            while ((line = br.readLine()) != null) {
                if (a==0)return line;
            }
        } catch (IOException e) {
            log.info("文件异常");
        } return "";
    }

    /**
     * 写入TXT文件
     */
    public static void writeFile(String pathname,int a) {
        log.info("写入"+pathname);
        try {
            File writeName = new File(pathname); // 相对路径，如果没有则要建立一个新的output.txt文件
            writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                out.write(""+a); // \r\n即为换行
                out.flush(); // 把缓存区内容压入文件
                System.out.println("文件写入完毕");
            }
        } catch (IOException e) {
            System.out.println("文件不存在!");
        }
    }

}
