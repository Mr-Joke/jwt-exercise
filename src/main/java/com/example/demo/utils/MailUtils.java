package com.example.demo.utils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Company: 3DXT
 * Author: Joker
 * DateTime: 2018/7/6 10:41
 **/
public class MailUtils {
    private static final String MAIL_HOST = "smtp.qq.com";
    private static final String MAIL_PROTOCOL = "smtp";
    private static final String MAIL_AUTH = "true";
    private static final String MAIL_USERNAME = "576781024";
    private static final String MAIL_AUTH_CODE = "qvltkdvrmgkvbdhg";
    private static final String MAIL_SENTOR = "576781024@qq.com";
    private static Properties properties = new Properties();

    static {
        properties.setProperty("mail.host", MAIL_HOST);
        properties.setProperty("mail.transport.protocol", MAIL_PROTOCOL);
        properties.setProperty("mail.smtp.auth", MAIL_AUTH);
    }

    public static void send(String receiver,String subject,String content) throws MessagingException {
        Session session = Session.getInstance(properties);
        session.setDebug(true);
        Transport ts = session.getTransport();
        ts.connect(MAIL_HOST, MAIL_USERNAME, MAIL_AUTH_CODE);
        Message message = createSimpleMail(session,receiver,subject,content);
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }

    private static MimeMessage createSimpleMail(Session session,String receiver,String subject,String content) throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(MAIL_SENTOR));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
        message.setSubject(subject);
        message.setContent(content, "text/html;charset=UTF-8");
        return message;
    }
}
