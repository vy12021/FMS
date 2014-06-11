package com.vy12021.framework.util.mail;

import javax.mail.MessagingException;
import java.util.List;

/**
 * Created by LIUYONG on 14-2-6.
 */
public class MailSenderDemo {

    /**
     *首先用工厂创建一个邮件发送实例
     */
    private SimpleMailSender simpleMailSender;

    private SimpleMail simpleMail;

    /**
     *收件人集合
     */
    public static void main(String[] args) {

        MailSenderDemo mailSenderDemo = new MailSenderDemo();
        mailSenderDemo.simpleMail = new SimpleMail();
        mailSenderDemo.simpleMailSender = MailSenderFactory.getSender(MailSenderType.STMP, "mail.properties");
        List<String> recipientList = mailSenderDemo.simpleMail.getRecipientList();
        String subject = mailSenderDemo.simpleMail.getSubject();
        String content = mailSenderDemo.simpleMail.getContent();
        subject = "分lfkdjsal；抗击非典是";
        content = "解放路口的沙洛夫爱的色放链接http://www.ysdvd.com";
        recipientList.add("576319111@qq.com");
        try {
            mailSenderDemo.simpleMailSender.send(recipientList, subject, content);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
