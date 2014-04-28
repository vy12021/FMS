package com.framework.util.mail;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LIUYONG on 14-2-6.
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class SimpleMail {

    /**
    * 收件地址
    */
    private List<String> recipientList = new ArrayList<String>();

    /**
     * 邮件主题
     * */
    private String subject;

    /**
     * 邮件内容
     * */
    private String content;

}
