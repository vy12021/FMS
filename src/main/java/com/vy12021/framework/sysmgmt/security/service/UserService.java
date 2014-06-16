package com.vy12021.framework.sysmgmt.security.service;

import com.vy12021.framework.BaseService;
import com.vy12021.framework.sysmgmt.index.model.Activate;
import com.vy12021.framework.sysmgmt.index.service.ActivateService;
import com.vy12021.framework.sysmgmt.security.dao.UserMapper;
import com.vy12021.framework.sysmgmt.security.model.User;
import com.vy12021.framework.util.mail.SendThread;
import com.vy12021.framework.util.mail.SimpleMail;
import com.vy12021.framework.util.paging.Pager;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by LIUYONG on 14-2-25.
 */
@Service
public class UserService extends BaseService<User, Long, UserMapper> {

    @Autowired
    private ActivateService activateService;

    @Autowired
    @Override
    public void setMapper(UserMapper mapper) {
        super.setMapper(mapper);
    }

    public User findByUsername(@Param("username")String username) {
        return this.getMapper().findByUsername(username);
    }

    public List<User> findPager(@Param("pager")Pager pager) {
        return this.getMapper().findPager(pager);
    }

    @Transactional
    public void register(User user) {
        if(user.getActivateStatus() == 0) {
            SimpleMail simpleMail = new SimpleMail();
            String uuid = UUID.randomUUID().toString();
            Activate activate = activateService.findByUserId(user.getId());
            if(activate == null) {
                activate = new Activate();
                activate.setUserId(user.getId());
                activate.setActivateId(uuid);
                activateService.save(activate);
            } else {
                activate.setActivateId(uuid);
                activateService.update(activate);
            }
            List<String> recipientList = new ArrayList<String>();
            recipientList.add(user.getUserInfo().getEmail());
            simpleMail.setRecipientList(recipientList);
            simpleMail.setSubject("注册本系统需要激活请进入邮件中连接进行操作");
            String activityUrl = SecurityUtils.getSubject().getSession().getAttribute("basePath") + "/admin/activate/" + uuid;
            simpleMail.setContent("尊敬的用户：" + user.getUsername() +
                    "&nbsp;<br/>&nbsp;&nbsp;&nbsp;&nbsp;刚才注册的用户需要邮箱激活请访问以下链接:<br/><a href=\"" +
                    activityUrl + "\">" + activityUrl + "</a>");

            new Thread(new SendThread(simpleMail)).start();
        }
    }
}
