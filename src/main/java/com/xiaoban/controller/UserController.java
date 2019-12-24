package com.xiaoban.controller;

import com.xiaoban.pojo.User;
import com.xiaoban.service.LogService;
import com.xiaoban.service.Richang;
import com.xiaoban.service.UserService;
import com.xiaoban.service.impl.IQz;
import com.xiaoban.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Author: xiaoban
 * @Date: 2019/8/22 5:47 PM
 */
@RestController
@RequestMapping(produces = "application/json;charset=utf-8")
@Slf4j
public class UserController {

    @Autowired
    private UserService us;
    @Autowired
    private LogService ls;
    private XiaobanData xbData = new XiaobanData(400);

    @GetMapping("/user")
    public XiaobanData getAll() {
        return new XiaobanData(200, "获取成功", us.getAll());
    }

    //抢座代码
    @GetMapping("/qz")
    public XiaobanData startQz(@RequestParam(value = "cnum", defaultValue = "0") int cnum,
                               @RequestParam(value = "fnum", defaultValue = "0") int fnum,
                               @RequestParam("name") String name,
                               @RequestParam(value = "type", defaultValue = "1") Integer type,
                               @RequestParam(value = "thread",defaultValue = "3")Integer thread,
                               @RequestParam(value = "time",defaultValue = "0")String time) {
        if (name == null) {
            return xbData;
        }
        User u = us.getByName(name);
        if (u == null) {
            return xbData;
        }
        if (u.getState()==1){
            //被锁定
            return xbData;
        }
        if (u.getRoomId()==0){
            cnum = (int) (1 + Math.random() * (4 - 1 + 1));
        }else {
            if (cnum==0)cnum=u.getRoomId();
        }
        if (!"0".equals(time)){
            Config.SEAT_END_TIME = time;
        }
        if (u.getSeatId()==0) {
            fnum = (int) (1 + Math.random() * (22 - 1 + 1));
        }else {
            if (fnum==0)fnum=u.getSeatId();
        }
        u.setRoomId(cnum);
        u.setSeatId(fnum);
        log.info("将为您优先选择["+u.getRoomId()+"]号房间的["+u.getSeatId()+"]号座位!");
        log.info("抢座运行!....");
        if (type == 2) {
            log.info("日常.....");
            Richang rc = new Richang(u);
            xbData.setData(rc.getUuid());
            for (int i = 0; i < thread; i++) {
                log.info("[线程]" + i);
                new Thread(rc).start();
            }
            xbData.setStatus(200);
            return xbData;

        } else {
            log.info("研究箱....");
            IQz qz = new IQz(u);
            qz.setLs(ls);
            qz.setUser(u);
            log.info("[" + u.getName() + "][线程]开始");
            for (int i = 0;i<thread;i++){
                new Thread(qz).start();
            }
            xbData.setStatus(200);
            xbData.setData(qz.getUuid());
            return xbData;
        }
    }

    @GetMapping("/log/{uuid}")
    public XiaobanData getLog(@PathVariable("uuid") String uuid) {
        xbData.setStatus(200);
        xbData.setInfo("请求成功");
        String a = "./xiaoban/"+DateUtil.getNowDateStr("YYYY-MM-dd")+"/qz-log.log";
        xbData.setData(X.readFile(a));
        return xbData;
    }

    @PostMapping("/user")
    public XiaobanData addUser(@RequestBody User u) {
        if (u == null) {
            xbData.setStatus(400);
            xbData.setInfo("参数错误!");
            return xbData;
        } else {
            if (us.getByName(u.getName()) != null) {
                xbData.setStatus(400);
                xbData.setInfo("用户名已存在!");
                return xbData;
            }
        }
        int i = us.addUser(u);
        if (i > 0) {
            xbData.setInfo("添加成功!");
            xbData.setStatus(200);
        }
        return xbData;
    }

    @GetMapping("/runAll")
    public XiaobanData runAll(@RequestParam(value = "group", defaultValue = "99") Integer group,
            @RequestParam(value = "debug", defaultValue = "0") Integer debug,
            @RequestParam(value = "time", defaultValue = "") String time,
                              @RequestParam(value = "key",defaultValue = "")String key) {
        if(!"".equals(time))Config.SEAT_END_TIME = time;
        Config.IS_DEBUG = debug == 1 && Config.KEY.equals(key);
        List<User> users = us.getAll();
        int i = 0;
        for (User u : users) {
            if (u.getState()==1)continue;
            if (group!=99){
                if (!u.getGroup().equals(group))continue;
            }
            log.info("研究箱.....");
            IQz qz = new IQz(u);
            if (u.getRoomId() == 0) {
                u.setRoomId((int) (1 + Math.random() * (4 - 1 + 1)));
            }
            if (u.getSeatId() == 0) {
                u.setSeatId((int) (1 + Math.random() * (22 - 1 + 1)));
            }
            log.info("[用户]" + u.getName() + "[线程]" + i++);
            new Thread(qz).start();
        }
        xbData.setStatus(200);
        xbData.setInfo("请求人数->"+i);
        return xbData;
    }

    @GetMapping("/success")
    public XiaobanData getSuccess() {
        int now  = Integer.parseInt(DateUtil.getNowDateStr("HH")) *3600+ (Integer.parseInt(DateUtil.getNowDateStr("mm"))*60+(Integer.parseInt(DateUtil.getNowDateStr("ss"))));
        int server = 6*3600+35*60;
        if (now<server) {
            try {
                log.info("睡眠"+(server-now)+"秒!");
                Thread.sleep((server-now)*1000);
            } catch (InterruptedException e) {
                log.error("睡眠异常");
            }
        }
        List<User> users = us.getAll();
        int i = 0;
        for (User u : users) {
            if (u.getState()!=0)continue;
            log.info("查询"+u.getName()+"抢座结果!");
            IsSuccess is = new IsSuccess();
            is.setU(u);
            new Thread(is).start();
        }
        xbData.setStatus(200);
        xbData.setInfo("请求成功!");
        xbData.setData("查询人数为:->" + i);
        return xbData;
    }

    @GetMapping("/config")
    public XiaobanData config(){
        log.info("开始系统配置!....");
        new Thread(S::start).start();
        xbData.setStatus(200);
        xbData.setInfo("请求成功!");
        return xbData;
    }

    @GetMapping("/cancel")
    public XiaobanData quxiao(@RequestParam("name")String name,@RequestParam("sno")String sno){
        //取消约座
        User u = us.getByName(name);
        if (u==null){
            xbData.setStatus(400);
            xbData.setInfo("查无此人");
        }else {
            if (sno==null||"".equals(sno)){
                xbData.setStatus(400);
                xbData.setInfo("请输入学号");
            }else {
                WebDriver w = QzUtils.getWebDriver();
                w.get(Config.getMyDailySession(u.getKey()));
                w.get(Config.BESPEAKCANCEL);
                try {
                    Thread.sleep(5000);
                    xbData.setStatus(200);
                    xbData.setInfo("请求成功！");
                }catch (Exception e){
                    xbData.setStatus(400);
                }finally {
                    w.quit();
                }
            }
        }
        return xbData;
    }
}
