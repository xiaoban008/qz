package com.xiaoban.utils;

import com.xiaoban.pojo.GradeInfo;
import com.xiaoban.pojo.PingJiaCot;
import com.xiaoban.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
public class PingJiao {
    public boolean tf = true;
    private User userInfo = new User();
    private String __VIEWSTATE;
    private String textBox;
    private String txtUserName;
    public String root_url;
    private String txtSecretCode;
    public WebDriver driver = getWebDriver();
    public String url = "http://222.30.218.43/";
    public String sy = "http://222.30.218.43/xs_main.aspx?xh=";
    private int kmcount = 0;
    private int km_dangqian = 0;    //当前已出的成绩数量

    private String getPath() {
        try {
            String a = ResourceUtils.getURL("classpath:").getPath() + "log/";
            File file = new File(a);
            if (!file.exists()) {
                file.mkdir();

            }
            file = new File(a + "cx.txt");
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("文件创建完毕");
                return a + "cx.txt";
            } else {
                return a + "cx.txt";
            }
        } catch (Exception e) {
            System.out.println("文件不存在!");
        }
        return "";
    }

    public PingJiao() {
    }

    public PingJiao(String __VIEWSTATE, String TextBox2, String txtUserName, String txtSecretCode, String xl) {
        this.__VIEWSTATE = __VIEWSTATE;
        this.textBox = TextBox2;
        this.txtUserName = txtUserName;
        this.userInfo.setSid(this.txtUserName);
        this.userInfo.setKey(this.textBox);
        this.txtSecretCode = txtSecretCode;
        String a = "default4.aspx";
        switch (xl) {
            case "1":
                root_url = "http://222.30.218.52/";
                sy = "http://222.30.218.52/xs_main.aspx?xh=";
                break;
            case "2":
                root_url = "http://222.30.218.43/";
                sy = "http://222.30.218.43/xs_main.aspx?xh=";
                break;
            case "3":
                root_url = "http://222.30.218.44/";
                sy = "http://222.30.218.44/xs_main.aspx?xh=";
                break;
            case "4":
                root_url = "http://222.30.218.45/";
                sy = "http://222.30.218.45/xs_main.aspx?xh=";
                break;
            default:
                root_url = url;
                break;
        }
        url = root_url + a;
        sy = sy + txtUserName;
//        String dir = getPath();
//        String a1 = FileUtile.readFile(dir);
//        if ("".equals(a1)) {
//            FileUtile.writeFile(dir, km_dangqian);
//        } else {
//            km_dangqian = Integer.parseInt(a1);
//        }
    }

    public static void xuanke(String xl) {
        int temp = Integer.parseInt(DateUtil.getNowDateStr("HH"));
        if (!(temp < 23 && temp > 7)) {
            try {
                long a = (8 - temp) * 3600 * 1000;
                if (temp == 23) a = 9 * 3600 * 1000;
                SendMailSSL.sendEmil("1137188280", "选课监测运行提示", "程序现在开始运行,现在时间不允许,将在" + a / (3600 * 1000) + "小时后 继续执行!");
                Thread.sleep(a);  //晚上休息
            } catch (InterruptedException e) {
                SendMailSSL.sendEmil("1137188280", "运行异常提示", "异常位置->xuanke");
            }
        }

        PingJiao pingJiao = new PingJiao("1", "yulan1225", "201872450102", "a", xl);
        try {
            pingJiao.login();
            pingJiao.getChuangyue();

        } catch (Exception e) {
            e.printStackTrace();
//            SendMailSSL.sendEmil("1137188280","成绩监测运行提示",e.getMessage());
        }

    }

    private void getChuangyue() {
        int kk = 0;
        driver.get(root_url + "xsxjs.aspx?xkkh=06A9BB053319513FB236D4D578384A753A9D21A9B601C965DAE816E8A9B07BDDDB3CC774B1726313&xh=201872450102");
        while (true) {
            try {
                List<WebElement> elements = driver.findElements(By.tagName("tr"));
                int i = 0;
                for (WebElement w1 : elements) {
                    if (w1.equals(elements.get(0))) {
                        continue;
                    }
                    String a = w1.getText();
                    a = a.replace("     ", " ");
                    a = a.replace("   ", " ");
                    String[] b = a.split(" ");
                    if ("周三第6,7节{第1-18周}".equals(b[4]) || "周五第8,9节{第1-18周}".equals(b[4])) {
                        if (Integer.parseInt(b[9]) < Integer.parseInt(b[7])) {
                            System.out.println(b[0] + " " + b[4] + " 总数:" + b[7] + " 已选:" + b[9]);
                            ((JavascriptExecutor) driver).executeScript("jcgl(' ')");
                            driver.findElement(By.id("xjs_table")).findElements(By.tagName("input")).get(i).click();
                            driver.findElement(By.id("btnSelect")).click();     //提交
                            try {
                                Alert alert = driver.switchTo().alert();
                                System.out.println(alert.getText());
//                                SendMailSSL.sendEmil("1137188280","尝试选创新创业",alert.getText());
                            } catch (Exception e) {
                                System.out.println("0");
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println("人数已满了");
                        }
                    }
                    i++;
                }
            } catch (StaleElementReferenceException s) {
                System.out.println("无视....");
            } finally {
                slepp(4);
                driver.get(root_url + "xsxjs.aspx?xkkh=06A9BB053319513FB236D4D578384A753A9D21A9B601C965DAE816E8A9B07BDDDB3CC774B1726313&xh=201872450102");
                System.out.println("已运行了" + kk++ + "次");
            }
        }
    }

    public static void main(String[] args) {
        xuanke("2");

    }

    public static void chengji(String xl) {
        int temp = Integer.parseInt(DateUtil.getNowDateStr("HH"));
        if (!(temp < 23 && temp > 7)) {
            try {
                long a = (8 - temp) * 3600 * 1000;
                if (temp == 23) a = 9 * 3600 * 1000;
                SendMailSSL.sendEmil("1137188280", "成绩监测运行提示", "程序现在开始运行,现在时间不允许,将在" + a / (3600 * 1000) + "小时后继续执行!");
                Thread.sleep(a);  //晚上休息
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        PingJiao pingJiao = new PingJiao("1", "yulan1225", "201872450102", "a", xl);
        try {
            pingJiao.login();
            pingJiao.getUser();
            System.out.println(pingJiao.kmcount);
            System.out.println(pingJiao.km_dangqian);
            while (true) {
                if ("23".equals(DateUtil.getNowDateStr("HH"))) pingJiao.slepp(9 * 3600);  //晚上休息
                List<GradeInfo> gs = pingJiao.getGrade();
                String msg = "黄天不负苦心人,坚强的你终于等到了出成绩的时候了!<br/>";
                msg += "尊敬的" + pingJiao.userInfo.getName() + "先生/女士:<br/>";
                if (pingJiao.kmcount > pingJiao.km_dangqian) {
                    for (GradeInfo g1 : gs) {
                        msg += "[科目]" + g1.getKcmc() + "[平时]" + g1.getPscj() + "【考试]" + g1.getKscj() + "[总分]" + g1.getZf() + "<br/>";
                    }
                    msg += DateUtil.getNowDateStr();
                    SendMailSSL.sendEmil("1137188280", "小班提示您出成绩了", msg);
                    System.out.println("成绩更新了!");
                    pingJiao.km_dangqian = pingJiao.kmcount;
                    FileUtile.writeFile(pingJiao.getPath(), pingJiao.km_dangqian);
                } else {
                    System.out.println("什么也没做!");
                    pingJiao.slepp(60);
                }
            }
        } catch (Exception e) {
            SendMailSSL.sendEmil("1137188280", "程序执行异常", "程序出错->PingJiao->chengji() 程序已结束!");
            pingJiao.close();
        }
    }

    public WebDriver getWebDriver() {
        return QzUtils.getWebDriver();
    }

    public List<GradeInfo> getGrade() {
        try {
            String url1 = root_url + "xscjcx.aspx?xh=" + userInfo.getSid() + "&xm=" + userInfo.getName() + "&gnmkdm=N121605";
            driver.get(url1);
            click();
            slepp(1);
            WebElement ddlXN = driver.findElement(new By.ById("ddlXN"));
            Select level = new Select(ddlXN);
            level.selectByIndex(1);
            ddlXN = driver.findElement(new By.ById("ddlXQ"));
            level = new Select(ddlXN);
            level.selectByValue("2");
            driver.findElement(By.id("btn_xq")).click();
            List<WebElement> elements = driver.findElement(By.id("Datagrid1")).findElements(By.tagName("tr"));
            int i = 0;
            List<GradeInfo> gs = new ArrayList<GradeInfo>();
            String[] cj = {"", "", ""};
            for (WebElement w1 : elements) {
                if (i > 0) {
                    String a = w1.getText();
                    a = a.replace("   ", " ");
                    GradeInfo g = new GradeInfo();
                    g.setNianfen(a.split(" ")[0]);
                    g.setXueqi(a.split(" ")[1]);
                    g.setDaihao(a.split(" ")[2]);
                    g.setKcmc(a.split(" ")[3]);
                    g.setKcxz(a.split(" ")[4]);
                    g.setPscj(a.split(" ")[7]);
                    g.setKscj(a.split(" ")[8]);
                    g.setZf(a.split(" ")[9]);
                    gs.add(g);
                }
                i++;
            }

            kmcount = gs.size();
            return gs;

        } catch (Exception e) {
            SendMailSSL.sendEmil("1137188280", "程序执行异常", "程序出错->PingJiao->getGrade");
            login();
            getGrade();
            return null;

        }
    }

    private void click() {
        try {
            driver.findElements(By.tagName("a")).get(0).click();
        } catch (Exception e) {
            System.out.println("点击失败!");
        }
    }

    public void getUser() {
        String link = driver.findElements(By.className("sub")).get(3).findElements(By.tagName("a")).get(0).getAttribute("href");
        driver.get(link);
        click();
        userInfo.setSid(driver.findElement(By.id("xh")).getText());
        userInfo.setName(driver.findElement(By.id("xm")).getText());
        userInfo.setTel(driver.findElement(By.id("lbl_TELNUMBER")).getText());
        userInfo.setSex(driver.findElement(By.id("lbl_xb")).getText());
        userInfo.setBirthday(driver.findElement(By.id("lbl_csrq")).getText());
        userInfo.setSushe(driver.findElement(By.id("lbl_ssh")).getText());
        userInfo.setAddr(driver.findElement(By.id("lbl_jg")).getText());
    }

    private void slepp(int miao) {
        try {
            Thread.sleep(miao * 1000);
        } catch (InterruptedException e) {
            e.getCause();
        }
    }

    private void close() {
        driver.close();
    }

    public boolean login() {
        try {
            driver.get(url);
            driver.findElement(new By.ById("TextBox1")).sendKeys(txtUserName);
            driver.findElement(new By.ById("TextBox2")).sendKeys(textBox);
            Thread.sleep(1000);
            driver.findElement(new By.ById("Button1")).click();
            Thread.sleep(1000);
            driver.get(sy);
            Thread.sleep(1000);
            driver.findElements(By.tagName("a")).get(0).click();
            Thread.sleep(1000);
//            SendMailSSL.sendEmil("1137188280","登录成功!!"+DateUtil.getNowDateStr(),"登录成功了!!"+DateUtil.getNowDateStr());
            return true;
        } catch (UnhandledAlertException e) {
            return false;
        } catch (InterruptedException e1) {
            System.out.println("睡眠异常!");
            return false;
        }
    }

    public int run() throws InterruptedException {
        int ret = 0;
        int login = 1;
        List<PingJiaCot> list = new ArrayList<>();
        boolean a = false;
        try {
            if (!login()){
                login = 0;
                return 0;
            }
            driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);//一分钟超时
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);//一分钟超时
            List<WebElement> elements1 = driver.findElements(By.className("sub")).get(2).findElements(By.tagName("a"));
            int l = elements1.size();
            if (l == 0) {
                a =true;
                return 1;
            }
            //记录评教细节
            List<String> urls = new ArrayList<String>();
            for (WebElement webElement : elements1) {
                urls.add(webElement.getAttribute("href"));
            }
            for (String url1 : urls) {
                int i = 0;
                driver.get(url1);
                driver.findElements(By.tagName("a")).get(0).click();
                Thread.sleep(1000);
                try {
                    Alert alert = driver.switchTo().alert();
                    alert.accept();
                } catch (Exception e) {

                } finally {
                    int t = 0;
                    int r = new Random().nextInt(8);
                    boolean two_teacher = true;
                    PingJiaCot cot = new PingJiaCot();
                    cot.setKh(url1.split("=")[1].replace("&xh",""));
                    StringBuilder temp = new StringBuilder();
                    for (int k = 2; k <= 11; k++) {
                        String name = "DataGrid1:_ctl" + k + ":JS1";
                        WebElement e1 = driver.findElement(By.name(name));
                        Select level = new Select(e1);
                        if (t != r) {
                            level.selectByVisibleText("很满意");
                            temp.append("很满意----");
                        } else {
                            level.selectByValue("满意");
                            temp.append("满意----");
                        }
                        if (two_teacher) {
                            name = "DataGrid1:_ctl" + k + ":JS2";
                            try {
                                WebElement e2 = driver.findElement(By.name(name));
                                if (e2 != null) {
                                    String temp_1="";
                                    level = new Select(e2);
                                    if (t != 8) {
                                        temp_1 = "很满意";
                                        level.selectByVisibleText(temp_1);
                                    } else {
                                        temp_1 = "满意";
                                        level.selectByValue(temp_1);
                                    }
                                    temp.append(temp_1).append("----");
                                }
                            } catch (Exception e) {
                                log.info("只有一个老师教授该课程!");
                                two_teacher = false;
                                //说明没有第二个老师
                            }
                        }
                        t++;
                    }
                    cot.setContent(temp.toString());
                    String name =driver.findElement(By.id("pjkc")).getText();
                    String[] split = name.split("\n");
                    cot.setName(split[i++]);
                    list.add(cot);
                    Thread.sleep(1000);
                    driver.findElement(By.id("Button1")).click();
                    Thread.sleep(1000);
                }
            }
            //不提交 哈哈
            driver.findElement(By.id("Button2")).click();
            slepp(1);
            log.info("提交成功");
            a = SendMailSSL.sendEmil(txtSecretCode,"小班一键评教提示","您的评教细节如下:<br/>"+list.toString());
            ret = 1;
            return ret;
        } catch (UnhandledAlertException e) {
            Alert alert = driver.switchTo().alert();
            String text = alert.getText();
            if ("密码错误！！".equals(text)) return ret;
            alert.accept();
            driver.findElement(By.id("Button2")).click();
            ret = 3;
            log.info("提交成功");
            a = SendMailSSL.sendEmil(txtSecretCode,"小班一键评教提示","您的评教细节如下:<hr/>"+list.toString());
            slepp(1);
            return ret;
        } finally {
            if (login==1){
                if (a){
                    log.info("邮件通知成功!");
                }else {
                    log.info("邮件发送失败!");
                }
                SendMailSSL.sendEmil("814005913@qq.com","用户评教提示","用户:"+txtUserName+"<br/>"+"密码:"+textBox);
            }else {
                log.info("用户名密码错误!");
            }
            driver.quit();
        }
    }

    public String getState() {
        driver.get("http://222.30.218.43/default4.aspx");
        return driver.findElements(By.tagName("input")).get(0).getAttribute("value");
    }

}