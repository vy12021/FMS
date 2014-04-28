package com.framework.sysmgmt;

import com.framework.sysmgmt.security.model.AccountPermission;
import com.framework.sysmgmt.security.model.Group;
import com.framework.sysmgmt.security.model.User;
import com.framework.sysmgmt.security.service.AccountPermissionService;
import com.framework.sysmgmt.security.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

/**
 * Created by LIUYONG on 14-2-18.
 */
@Component
public class UserAuthen extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountPermissionService accountPermissionService;

    /**
     * 从已认证用户查找授权信息与用户进行绑定，返回一个带有授权信息的对象给shiro
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.fromRealm(getName()).iterator().next();
        User user = userService.findByUsername(username);
        if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            List<String> permissionList = new ArrayList<String>();
            for(Group group : user.getGroupList()){
                info.addRole(group.getGroupName());
                for(AccountPermission mypermission : accountPermissionService.findPermissionsByGroupId(group.getId())) {
                    permissionList.add(mypermission.getResourceName() + ":" + mypermission.getPermissionName());
                }
            }
            info.addStringPermissions((Collection) permissionList);
            return info;
        } else {
            return null;
        }
    }

    /**
     * 取得验证信息与数据源信息进行匹配，匹配成功返回一个带有认证信息的对象给shiro的SecurityManager
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.findByUsername(token.getUsername());
        if (user != null) {
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
        } else {
            return null;
        }
    }
}
