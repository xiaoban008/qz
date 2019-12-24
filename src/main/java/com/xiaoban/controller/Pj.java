package com.xiaoban.controller;

import com.xiaoban.utils.PingJiao;
import com.xiaoban.utils.XiaobanData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Pj {
    private XiaobanData xbData = new XiaobanData(400);

    @RequestMapping("/pj")
    public XiaobanData pj(String __VIEWSTATE, String pw, String sid, String email, String xl) throws Exception {
        PingJiao pj = new PingJiao(__VIEWSTATE, pw, sid, email, xl);
        int res = pj.run();
        String info = "";
        if (res == 1) {
            xbData.setStatus(200);
            info = "哈哈，小班帮您完成了所有评教！不放心请自行登陆教务系统查看！";
        } else {
            xbData.setStatus(400);
            if (res == 0)
                info = "账号/密码错误，操作失败，请重试！请登陆教务系统查看账号密码及是否有评教任务！";
            else
                info = "请求失败，请重试！请登陆教务系统查看账号密码及是否有评教任务！";
        }
        xbData.setInfo(info);
        return xbData;
    }

}
