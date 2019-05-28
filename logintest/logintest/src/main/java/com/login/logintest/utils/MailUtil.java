package com.login.logintest.utils;


import com.login.logintest.domain.User;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.util.Properties;

public class MailUtil  {
    private String email;//receiver
    private String code;

    public MailUtil(String email){
        this.email = email;
    }

    public MailUtil(String email,String code){
        this.email = email;
        this.code = code;
    }

    /**
     * 用于向客户端发送激活邮件
     * @param user
     */
    public void sendMailToActive(User user){
        //1.create javax.mail.session
        //2.create javax.mail.message
        //3.send

        String from = "x11337@126.com";
        String host = "smtp.126.com";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host",host);//set mail server
        properties.setProperty("mail.smtp.auth","true");//open check

        try{
            // 1.获取默认session对象
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("x11337@126.com", "cuigang123"); // 发件人邮箱账号、授权码
                }
            });

            // 2.创建邮件对象
            Message message = new MimeMessage(session);
            // 2.1设置发件人
            message.setFrom(new InternetAddress(from));
            // 2.2设置接收人
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            // 2.3设置邮件主题
            message.setSubject("账号激活");
            // 2.4设置邮件内容
            String content = "<html><head></head><body><h1>这是一封激活邮件,激活请点击以下链接</h1>" +
                    "<h3><a href='http://localhost:8080/RegisterDemo/ActiveServlet?code="
                    + code + "'>http://localhost:8888/active?name=" + user.getName()+"&code="+user.getCode()
                    + "</a>" + "</h3>" +"<h3>你的账号是："+user.getName()+"</br>"+
                    "你的密码是："+user.getPassword()+"</br>" +
                    "请勿将账号密码泄露给其他人"+
                    "</body></html>";
            message.setContent(content, "text/html;charset=UTF-8");
            // 3.发送邮件
            Transport.send(message);
            System.out.println("邮件成功发送!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @param fileName 路径+文件名
     *
     */
    public void sendAnalyseResult(String fileName){
        //1.create javax.mail.session
        //2.create javax.mail.message
        //3.send

        String from = "x11337@126.com";
        String host = "smtp.126.com";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host",host);//set mail server
        properties.setProperty("mail.smtp.auth","true");//open check

        try {
            // 1.获取默认session对象
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("x11337@126.com", "cuigang123"); // 发件人邮箱账号、授权码
                }
            });
            // 2.创建邮件对象
            Message message = new MimeMessage(session);
            // 2.1设置发件人
            message.setFrom(new InternetAddress(from));
            // 2.2设置接收人
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            // 2.3设置邮件主题
            message.setSubject("分析结果送达");

            // 创建消息部分
            BodyPart messageBodyPart = new MimeBodyPart();

            // 消息
            messageBodyPart.setText("这是您的分析结果，请查收附件");

            // 创建多重消息
            Multipart multipart = new MimeMultipart();

            // 设置文本消息部分
            multipart.addBodyPart(messageBodyPart);

            // 附件部分
            messageBodyPart = new MimeBodyPart();


            DataSource source = new FileDataSource(fileName);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);

            // 发送完整消息
            message.setContent(multipart);

            //   发送消息
            Transport.send(message);
            System.out.println("Sent message successfully....");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
