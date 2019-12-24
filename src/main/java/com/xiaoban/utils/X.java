package com.xiaoban.utils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 系统配置辅助类
 * @Author: xiaoban
 * @Date: 2019/9/1 3:21 PM
 */
@Slf4j
public class X {
    public static void copy(String srcPathStr, String fileName, String desPathStr) {
        //获取源文件的名称
        File file = new File(desPathStr);
        if (!file.exists()) {
            boolean f = file.mkdir();
            if (f) log.info("创建文件夹成功!");
        }
        String newFileName = fileName;
        desPathStr = desPathStr + File.separator + newFileName; //源文件地址
        srcPathStr = srcPathStr + File.separator + newFileName; //源文件地址
        log.info("文件复制:[" + srcPathStr + "]-->[" + desPathStr + "]");
        try {
            FileInputStream fis = new FileInputStream(srcPathStr);//创建输入流对象
            FileOutputStream fos = new FileOutputStream(desPathStr); //创建输出流对象
            byte datas[] = new byte[1024 * 8];//创建搬运工具
            int len = 0;//创建长度
            while ((len = fis.read(datas)) != -1)//循环读取数据
            {
                fos.write(datas, 0, len);
            }
            fis.close();//释放资源
            fis.close();//释放资源
        } catch (Exception e) {
            e.printStackTrace();
            log.info("文件移动失败!");
        }
    }

    public static void openExe(String path) {
        Runtime rn = Runtime.getRuntime();
        Process p = null;
        try {
            p = rn.exec("\"" + path + "\"");
        } catch (Exception e) {
            System.out.println("Error exec!");
            e.printStackTrace();
        }
    }

    public static boolean unZip(File zipFile, String descDir, List<String> urlList) {
        boolean flag = false;
        File pathFile = new File(descDir);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        ZipFile zip = null;
        try {
            //指定编码，否则压缩包里面不能有中文目录
            zip = new ZipFile(zipFile, Charset.forName("gbk"));
            for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String zipEntryName = entry.getName();
                InputStream in = zip.getInputStream(entry);
                String outPath = (descDir + zipEntryName).replace("/", File.separator);
                //判断路径是否存在,不存在则创建文件路径
                File file = new File(outPath.substring(0, outPath.lastIndexOf(File.separator)));
                if (!file.exists()) {
                    file.mkdirs();
                }
                //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
                if (new File(outPath).isDirectory()) {
                    continue;
                }
                //保存文件路径信息
                urlList.add(outPath);

                OutputStream out = new FileOutputStream(outPath);
                byte[] buf1 = new byte[2048];
                int len;
                while ((len = in.read(buf1)) > 0) {
                    out.write(buf1, 0, len);
                }
                in.close();
                out.close();
            }
            flag = true;
            //必须关闭，否则无法删除该zip文件
            zip.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static String readFile(String pathname) {
        StringBuilder a = new StringBuilder();
        List<String> b = new ArrayList<>();
        List<String> c = new ArrayList<>();
        try (FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                b.add(line);
            }
            int len = 10;
            if (b.size()<=10)len = b.size();
            for (int i = b.size();b.size()-i<len;i--){
                c.add(b.get(i - 1)+"<br/>");
            }
            len = 10;
            if (c.size()<=10)len = c.size();
            for (int i = c.size();c.size()-i<len;i--){
                a.append(c.get(i-1));
            }
        } catch (IOException e) {
            System.out.println("读取日志异常!");
        }
        return a.toString();
    }

    public static String readFileS(String pathname) {
        StringBuilder a = new StringBuilder();
        try (FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                a.append(line).append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return a.toString();
    }
}
