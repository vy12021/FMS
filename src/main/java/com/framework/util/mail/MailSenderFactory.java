package com.framework.util.mail;


/**
 * Created by LIUYONG on 14-2-6.
 */

public class MailSenderFactory {

    /**
     * 服务邮箱
     */
    private static SimpleMailSender simpleMailSender;

    /**
     * 获取邮箱
     * @param type 邮箱类型
     * @return 符合类型的邮箱
     */
    public static SimpleMailSender getSender(String type, String path) {
        if (type == MailSenderType.STMP) {
            if (simpleMailSender == null) {
                simpleMailSender = new SimpleMailSender(path);
            }
            return simpleMailSender;
        }
        return null;
    }

}
