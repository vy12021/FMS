package com.vy12021.framework.sysmgmt.security.service;

import com.vy12021.framework.BaseService;
import com.vy12021.framework.sysmgmt.security.dao.AccountPermissionMapper;
import com.vy12021.framework.sysmgmt.security.model.AccountPermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LIUYONG on 14-2-25.
 */
@Service
public class AccountPermissionService extends BaseService<AccountPermission, Integer, AccountPermissionMapper> {

    @Autowired
    @Override
    public void setMapper(AccountPermissionMapper mapper) {
        super.setMapper(mapper);
    }

    public List<AccountPermission> findPermissionsByGroupId(@Param("groupId") Integer groupId) {
        return this.getMapper().findPermissionsByGroupId(groupId);
    }
}
