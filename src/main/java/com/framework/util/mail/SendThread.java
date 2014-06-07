package com.framework.util.mail;

import com.springapp.mvc.model.ActivateUser;
import com.springapp.mvc.mybatis.service.ActivateService;
import com.framework.sysmgmt.security.model.User;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by LIUYONG on 14-2-13.
 */
public class SendThread implements Runnable {

    private User user;
    private ActivateUser activateUser;
    private String bathPath;
    private ActivateService activateService;

    public SendThread(User user, ActivateUser activateUser, String bathPath, ActivateService activateService) {
        this.activateUser = activateUser;
        this.bathPath = bathPath;
        this.user = user;
        this.activateService = activateService;
    }

    @Override
    public void run() {
        if("0".equals(user.getActivateStatus())) {
            SimpleMail simpleMail = new SimpleMail();
            String uuid = UUID.randomUUID().toString();
            if(activateUser == null) {
                activateUser = new ActivateUser();
                activateUser.setUserId(user.getId());
                activateUser.setActivateId(uuid);
                activateService.save(activateUser);
            } else {
                activateUser.setActivateId(uuid);
                activateService.update(activateUser);
            }
            List<String> recipientList = new ArrayList<String>();
            recipientList.add(user.getUserInfo().getEmail());
            simpleMail.setRecipientList(recipientList);
            simpleMail.setSubject("注册本系统需要激活请进入邮件中连接进行操作");
            String activityUrl = bathPath + "/register/activate/" + uuid;
            simpleMail.setContent("尊敬的用户：" + user.getUsername() +
                    "&nbsp;<br/>&nbsp;&nbsp;&nbsp;&nbsp;刚才注册的用户需要邮箱激活请访问以下链接:<br/><a href=\"" +
                    activityUrl + "\">" + activityUrl + "</a>");
            try {
                MailSenderFactory.getSender(MailSenderType.STMP, "mail.properties").send(simpleMail);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}
