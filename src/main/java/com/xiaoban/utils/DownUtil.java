package com.xiaoban.utils;

import java.net.HttpURLConnection;
import java.text.DecimalFormat;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
/*
 * 网络爬虫取数据
 *
 * */
@Slf4j
public class DownUtil {
    public static  int process = 0;
    private static String getPath() {
        String a = "./xiaoban/img";
        try {
            File file = new File(a);
            if (!file.exists()) {
                if(!file.mkdir()) log.info("目录生成失败了！");
            }
        } catch (Exception e) {
            log.info("ResourceUtils运行失败");
            a = "";
        }
        return a;

    }
    public static boolean Down(String imgUrl,String name){
        URL url;
        try {
            File file = new File(getPath()+name+".png");
            if (file.exists()){ return true;}
            String a = file.getPath();
            url = new URL(imgUrl);
            // 打开URL连接
            URLConnection con = url.openConnection();
            // 得到URL的输入流
            con.getContent();
            InputStream input = con.getInputStream();
            // 设置数据缓冲
            byte[] bs = new byte[1024 * 2];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存图片至本地
            OutputStream os = new FileOutputStream(a);
            while ((len = input.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            os.close();
            input.close();
            return true;
        } catch (MalformedURLException e) {
            log.info("[图片]出现异常！代码01");
            return false;
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            log.info("[图片]出现异常！代码02");
            File file = new File("img");
            file.mkdir();
            log.info(file.getAbsolutePath());
            return true;
        }
    }
    public static boolean  downLoadFromUrl(String urlStr,String fileName,String savePath) {
        String token="v32Eo2Tw+qWI/eiKW3D8ye7l19mf1NngRLushO6CumLMHIO1aryun0/Y3N3YQCv/TqzaO/TFHw4=";
        process = 0;
        try {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(3*1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.1 (compatible; MSIE 5.0; Windows NT; DigExt)");
        conn.setRequestProperty("lfwywxqyh_token",token);
        //得到输入流
        InputStream inputStream = conn.getInputStream();
        log.info("[文件大小]"+conn.getContentLength()/100+"kb");


            //获取自己数组
        byte[] getData = readInputStream(inputStream,conn.getContentLength());

        //文件保存位置
        File saveDir = new File(savePath);
        if(!saveDir.exists()){
            saveDir.mkdir();
        }
        File file = new File(saveDir+File.separator+fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if(fos!=null){
            fos.close();
        }
        if(inputStream!=null){
            inputStream.close();
        }
        log.info("下载完成!");
        process = 100;
        return true;
        }catch (Exception e){
            log.info("下载出错!");
            return false;
        }
    }



    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static  byte[] readInputStream(InputStream inputStream,Integer size) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        String dw1 = "b";
        String dw2 = "b";
        Integer size1 = size;
        if ((size/1204)>=1024){
            dw2 = "MB";
            size = size/(1024*1024);
        }else if ((size/1204)>=1){
            dw2 = "kb";
            size = size/(1024);
        }
        Double size2 = 0.0;
        while((len = inputStream.read(buffer)) != -1) {
            size2 +=Math.abs(len);
            Integer nowSize = (bos.size()/1024);
            if ((nowSize)>=1024){
                dw1 = "MB";
                nowSize = nowSize/(1024);
            }else if ((nowSize)>=1){
                dw1 = "kb";
            }
            Double bfb = (size2*100)/size1;
            DecimalFormat df = new DecimalFormat("#");
            Integer bfb1 = Integer.valueOf(df.format(bfb));
            if (bfb1==process+1){
                log.info("正在下载文件-->"+nowSize+dw1+"/"+size+dw2+"\t"+process+"%");
                process = bfb1;
            }

            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

}