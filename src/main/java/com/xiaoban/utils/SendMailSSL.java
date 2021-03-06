package com.xiaoban.utils;

/**
 * @Description: 邮箱发送
 * @Author: xiaoban
 * @Date: 2019/7/1 10:08 PM
 */


import java.security.Security;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * javaMail的邮件工具类
 *
 * @author wangXgnaw
 */
@Slf4j
public class SendMailSSL {
    public static  String personal = "小班的来信";
    public static String signature = "小班抢座提示";
    public static  boolean sendEmil(MailInfo mailInfo) {
        String to =mailInfo.getTo();
        String tittle = mailInfo.getTitle();
        String message = mailInfo.getMsg();
        String username = "15885713703@163.com";
        String password = "aaaa19970422";
        //空的不发送
        message ="<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "    <title>小班的来信</title>\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                + "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>\n"
                + "    <meta name=\"keywords\" content=\"Happy New year Newsletter templates, Email Templates, Newsletters, Marketing  templates,\n"
                + "\tAdvertising templates, free Newsletter\"/>\n"
                + "    <style type=\"text/css\">\n"
                + "        table.container {\n"
                + "            box-shadow: 0 0 0.6em #ccc;\n"
                + "            -webkit-box-shadow: 0 0 0.6em #ccc;\n"
                + "            -o-box-shadow: 0 0 0.6em #ccc;\n"
                + "            -moz-box-shadow: 0 0 0.6em #ccc;\n"
                + "            -ms-box-shadow: 0 0 0.6em #ccc;\n"
                + "            border-radius: 30px;\n"
                + "        }\n"
                + "        body {\n"
                + "            width: 100%;\n"
                + "            margin: 0;\n"
                + "            padding: 0;\n"
                + "        }\n"
                + "        table {\n"
                + "            font-size: 14px;\n"
                + "            border: 0;\n"
                + "        }\n"
                + "        @media only screen and (max-width: 800px) {\n"
                + "            .container {\n"
                + "                width: 754px !important;\n"
                + "            }\n"
                + "        }\n"
                + "\n"
                + "        @media only screen and (max-width: 768px) {\n"
                + "            .header-top {\n"
                + "                width: 700px;\n"
                + "            }\n"
                + "\n"
                + "            .header-bg {\n"
                + "                width: 440px !important;\n"
                + "                height: 10px !important;\n"
                + "            }\n"
                + "\n"
                + "            .main-header {\n"
                + "                line-height: 28px !important;\n"
                + "            }\n"
                + "\n"
                + "            /*----- --features ---------*/\n"
                + "            .feature-middle {\n"
                + "                width: 400px !important;\n"
                + "                text-align: center !important;\n"
                + "            }\n"
                + "\n"
                + "            .feature-img {\n"
                + "                height: 437px !important;\n"
                + "            }\n"
                + "\n"
                + "            .about-left {\n"
                + "                width: 275px;\n"
                + "            }\n"
                + "\n"
                + "            .about-right {\n"
                + "                width: 289px;\n"
                + "            }\n"
                + "\n"
                + "            .container {\n"
                + "                width: 654px !important;\n"
                + "            }\n"
                + "\n"
                + "            .container-middle {\n"
                + "                width: 590px !important;\n"
                + "            }\n"
                + "\n"
                + "            .member {\n"
                + "                width: 13px;\n"
                + "            }\n"
                + "\n"
                + "            table.main-img {\n"
                + "                height: 400px;\n"
                + "            }\n"
                + "        }\n"
                + "\n"
                + "        @media only screen and (max-width: 640px) {\n"
                + "            .logo {\n"
                + "                font-size: 0.85em;\n"
                + "            }\n"
                + "\n"
                + "            .container {\n"
                + "                width: 530px !important\n"
                + "            }\n"
                + "\n"
                + "            .main-img {\n"
                + "                width: 100% !important;\n"
                + "                height: 380px !important;\n"
                + "            }\n"
                + "\n"
                + "            .container-middle {\n"
                + "                width: 500px !important\n"
                + "            }\n"
                + "\n"
                + "            .about-left {\n"
                + "                width: 225px;\n"
                + "            }\n"
                + "\n"
                + "            .about-right {\n"
                + "                width: 226px;\n"
                + "                font-size: 0.98em;\n"
                + "            }\n"
                + "\n"
                + "            .mainContent {\n"
                + "                width: 550px;\n"
                + "                font-size: 0.98em;\n"
                + "            }\n"
                + "\n"
                + "            .feature {\n"
                + "                height: 136px;\n"
                + "                width: 200px;\n"
                + "            }\n"
                + "\n"
                + "            table.news-info {\n"
                + "                width: 210px;\n"
                + "            }\n"
                + "\n"
                + "            table.news-image {\n"
                + "                width: 282px;\n"
                + "            }\n"
                + "\n"
                + "            a.logo {\n"
                + "                font-size: 3em !important;\n"
                + "            }\n"
                + "\n"
                + "            table.footer {\n"
                + "                width: 500px;\n"
                + "            }\n"
                + "        }\n"
                + "\n"
                + "        @media only screen and (max-width: 480px) {\n"
                + "            .logo {\n"
                + "                font-size: 0.85em;\n"
                + "                margin-top: .5em\n"
                + "            }\n"
                + "\n"
                + "            .container-middle {\n"
                + "                width: 390px !important;\n"
                + "            }\n"
                + "\n"
                + "            .about-right {\n"
                + "                width: 299px\n"
                + "            }\n"
                + "\n"
                + "            .main-img {\n"
                + "                width: 450px !important;\n"
                + "                height: 259px !important;\n"
                + "            }\n"
                + "\n"
                + "            .feature1 {\n"
                + "                width: 300px;\n"
                + "            }\n"
                + "\n"
                + "            .container {\n"
                + "                width: 400px !important;\n"
                + "            }\n"
                + "\n"
                + "            .feature {\n"
                + "                height: 185px;\n"
                + "                width: 417px;\n"
                + "                text-align: Center;\n"
                + "            }\n"
                + "\n"
                + "            .gallery2 {\n"
                + "                margin: 2em 0;\n"
                + "            }\n"
                + "\n"
                + "            .fet {\n"
                + "                margin-top: 2em;\n"
                + "            }\n"
                + "\n"
                + "            .wel {\n"
                + "                font-size: 2em !important;\n"
                + "            }\n"
                + "\n"
                + "            .top-header-left {\n"
                + "                font-size: 0.94em;\n"
                + "            }\n"
                + "\n"
                + "            table.banner-bottom-text {\n"
                + "                width: 420px;\n"
                + "            }\n"
                + "\n"
                + "            .mainContent {\n"
                + "                width: 420px;\n"
                + "            }\n"
                + "\n"
                + "            table.footer {\n"
                + "                width: 420px;\n"
                + "            }\n"
                + "\n"
                + "            td.main-subheader {\n"
                + "                text-align: Center !important;\n"
                + "                margin-top: 20px;\n"
                + "            }\n"
                + "\n"
                + "            table.news-info {\n"
                + "                width: 100%;\n"
                + "            }\n"
                + "\n"
                + "            table.news-image {\n"
                + "                width: 100%;\n"
                + "            }\n"
                + "        }\n"
                + "\n"
                + "        @media only screen and (max-width: 375px) {\n"
                + "            .container {\n"
                + "                width: 350px !important;\n"
                + "            }\n"
                + "\n"
                + "            table.banner-bottom-text {\n"
                + "                width: 350px;\n"
                + "            }\n"
                + "\n"
                + "            .mainContent {\n"
                + "                width: 350px;\n"
                + "            }\n"
                + "\n"
                + "            table.footer {\n"
                + "                width: 350px;\n"
                + "            }\n"
                + "\n"
                + "            .container-middle {\n"
                + "                width: 250px !important;\n"
                + "            }\n"
                + "        }\n"
                + "\n"
                + "        @media only screen and (max-width: 320px) {\n"
                + "            .logo {\n"
                + "                font-size: 0.85em;\n"
                + "                width: 100%;\n"
                + "                text-align: center;\n"
                + "            }\n"
                + "\n"
                + "            .container {\n"
                + "                width: 296px !important;\n"
                + "            }\n"
                + "\n"
                + "            .main-img {\n"
                + "                width: 297px !important;\n"
                + "                height: 198px !important;\n"
                + "            }\n"
                + "\n"
                + "            .container-middle {\n"
                + "                width: 265px !important;\n"
                + "            }\n"
                + "\n"
                + "            .feature {\n"
                + "                height: 185px;\n"
                + "                width: 266px;\n"
                + "            }\n"
                + "\n"
                + "            .feature1 {\n"
                + "                width: 265px;\n"
                + "            }\n"
                + "\n"
                + "            .about-right {\n"
                + "                width: 260px;\n"
                + "            }\n"
                + "\n"
                + "            .gallery1 {\n"
                + "                width: 279px;\n"
                + "                height: 137px;\n"
                + "            }\n"
                + "\n"
                + "            .galry1 {\n"
                + "                height: 150px;\n"
                + "            }\n"
                + "\n"
                + "            .nav {\n"
                + "                width: 100%;\n"
                + "                text-align: center;\n"
                + "            }\n"
                + "\n"
                + "            .top {\n"
                + "                height: 20px;\n"
                + "            }\n"
                + "\n"
                + "            .container {\n"
                + "                width: 350px !important;\n"
                + "            }\n"
                + "\n"
                + "            table.banner-bottom-text {\n"
                + "                width: 350px;\n"
                + "            }\n"
                + "\n"
                + "            .mainContent {\n"
                + "                width: 350px;\n"
                + "            }\n"
                + "\n"
                + "            table.footer {\n"
                + "                width: 350px;\n"
                + "            }\n"
                + "\n"
                + "            .container-middle {\n"
                + "                width: 250px !important;\n"
                + "            }\n"
                + "\n"
                + "            a.logo {\n"
                + "                font-size: 2em !important;\n"
                + "            }\n"
                + "\n"
                + "            table.banner-bottom-text {\n"
                + "                width: 280px;\n"
                + "            }\n"
                + "\n"
                + "            .mainContent {\n"
                + "                width: 280px;\n"
                + "            }\n"
                + "\n"
                + "            table.footer {\n"
                + "                width: 280px;\n"
                + "            }\n"
                + "\n"
                + "            .container {\n"
                + "                width: 050px !important;\n"
                + "            }\n"
                + "\n"
                + "            table.social-icons {\n"
                + "                text-align: center;\n"
                + "                width: 69%;\n"
                + "            }\n"
                + "        }\n"
                + "\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "<table>\n"
                + "    <tr>\n"
                + "        <td height=\"40\" class=\"top\"></td>\n"
                + "    </tr>\n"
                + "</table>\n"
                + "<table width=\"800\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"container\">\n"
                + "    <tbody>\n"
                + "    <tr>\n"
                + "        <td>\n"
                + "            <table width=\"800\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\n"
                + "                   class=\"container\">\n"
                + "                <tbody>\n"
                + "                <tr bgcolor=\"ffffff\">\n"
                + "                    <td>\n"
                + "                        <table border=\"0\" width=\"650\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"\n"
                + "                               class=\"container-middle\">\n"
                + "                            <tr>\n"
                + "                                <td>\n"
                + "                                    <table border=\"0\" width=\"650\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"\n"
                + "                                           class=\"container-middle\">\n"
                + "\n"
                + "                                        <tr>\n"
                + "                                            <td height=\"22\"></td>\n"
                + "                                        </tr>\n"
                + "\n"
                + "                                        <tr class=\"top-header-left\">\n"
                + "                                            <td align=\"center\">\n"
                + "                                                <a class=\"logo\" href=\"#\"\n"
                + "                                                   style=\"font-size:3.5em;color: #69B922;text-decoration:none;font-family:Century Gothic;font-weight:216; letter-spacing:2px;\">\n"
                + "                                                    "+tittle
                + "</a>\n"
                + "                                            </td>\n"
                + "                                        </tr>\n"
                + "\n"
                + "                                    </table>\n"
                + "                                </td>\n"
                + "                            <tr>\n"
                + "                                <td height=\"20\"></td>\n"
                + "                            </tr>\n"
                + "                            </tr>\n"
                + "                        </table>\n"
                + "                    </td>\n"
                + "                </tr>\n"
                + "                <tr>\n"
                + "                    <td bgcolor=\"#0492CE\">\n"
                + "                        <table width=\"560\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" class=\"footer\">\n"
                + "                            <tr>\n"
                + "                                <td height=\"30\"></td>\n"
                + "                            </tr>\n"
                + "                            <tr>\n"
                + "                                <td>\n"
                + "                                    <!--footer-logo -->\n"
                + "                                    <table border=\"0\" align=\"left\" cellpadding=\"0\" cellspacing=\"0\"\n"
                + "                                           style=\"border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;\"\n"
                + "                                           class=\"logo\">\n"
                + "                                        <tr>\n"
                + "                                            <td height=\"11\"></td>\n"
                + "                                        </tr>\n"
                + "                                        <tr>\n"
                + "                                            <td style=\"color: white;font-size: 1.5em;padding-left: 2em;\">\n"
                + "                                                "+message
                + "                                            </td>\n"
                + "                                        </tr>\n"
                + "                                    </table>\n"
                + "                                </td>\n"
                + "                            </tr>\n"
                + "                            <tr>\n"
                + "                                <td height=\"15\"></td>\n"
                + "                            </tr>\n"
                + "                            <tr>\n"
                + "                                <td height=\"30\"></td>\n"
                + "                            </tr>\n"
                + "                        </table>\n"
                + "                    </td>\n"
                + "                </tr>\n"
                + "                <tr>\n"
                + "                    <td bgcolor=\"#1abbfe\">\n"
                + "                <tr>\n"
                + "                    <td bgcolor=\"#1abbfe\" height=\"20\"></td>\n"
                + "                </tr>\n"
                + "                <tr>\n"
                + "                    <td style=\"font-family: Candara; font-size: 1em; color: #fff;text-align:center; line-height: 24px;background-color:#1abbfe;\"\n"
                + "                        class=\"editable\">\n"
                + "                        "+signature
                + "                    </td>\n"
                + "                </tr>\n"
                + "                <tr>\n"
                + "                    <td bgcolor=\"#1abbfe\" height=\"20\"></td>\n"
                + "                </tr>\n"
                + "                </td>\n"
                + "                </tr>\n"
                + "                <!--footer-->\n"
                + "                </tbody>\n"
                + "            </table>\n"
                + "        </td>\n"
                + "    </tr>\n"
                + "    </tbody>\n"
                + "</table>\n"
                + "<table>\n"
                + "    <tr>\n"
                + "        <td height=\"40\" class=\"top\"></td>\n"
                + "    </tr>\n"
                + "</table>\n"
                + "\n"
                + "</body>\n"
                + "</html>";
        if (to.length() == 0)return false;
        try {
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
            //设置邮件会话参数
            Properties props = new Properties();
            //邮箱的发送服务器地址
            props.setProperty("mail.smtp.host", "smtp.163.com");
            props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            //邮箱发送服务器端口,这里设置为465端口
            props.setProperty("mail.smtp.port", "465");
            props.setProperty("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.auth", "true");

            //获取到邮箱会话,利用匿名内部类的方式,将发送者邮箱用户名和密码授权给jvm
            Session session = Session.getDefaultInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            //通过会话,得到一个邮件,用于发送
            Message msg = new MimeMessage(session);
            //设置发件人
            msg.setFrom(new InternetAddress(username));
            msg.setFrom(new InternetAddress(username,personal,"UTF-8"));

            //设置收件人,to为收件人,cc为抄送,bcc为密送
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(to, false));
            msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(to, false));
            msg.setSubject(tittle);
            //设置邮件消息
            msg.setText(message);
            //设置发送的日期
            msg.setContent(message, "text/html;charset=UTF-8");
            //
            msg.setSentDate(new Date());
            //调用Transport的send方法去发送邮件
            Transport.send(msg);
            return true;
        } catch (Exception e) {
            log.info("邮件发送异常");
            e.printStackTrace();
            return false;
        }

    }

    public static boolean sendEmil(String to, String titile, String cot) {
        MailInfo m = new MailInfo();
        m.setTitle(titile);
        m.setTo(to);
        m.setMsg(cot);
        if(to!=null&&!"".equals(to)) return sendEmil(m);else return false;
    }
}