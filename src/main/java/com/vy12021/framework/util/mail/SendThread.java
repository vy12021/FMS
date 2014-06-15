package com.vy12021.framework.util.mail;

import javax.mail.MessagingException;

/**
 * Created by LIUYONG on 14-2-13.
 */
public class SendThread implements Runnable {

    private SimpleMail simpleMail;

    public SendThread(SimpleMail simpleMail) {
        this.simpleMail = simpleMail;
    }

    @Override
    public void run() {
        try {
            MailSenderFactory.getSender(MailSenderType.STMP, "mail.properties").send(simpleMail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
