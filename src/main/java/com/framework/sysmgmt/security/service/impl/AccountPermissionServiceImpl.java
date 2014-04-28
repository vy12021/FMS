package com.framework.sysmgmt.security.service.impl;

import com.framework.sysmgmt.security.dao.AccountPermissionMapper;
import com.framework.sysmgmt.security.model.AccountPermission;
import com.framework.sysmgmt.security.service.AccountPermissionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by LIUYONG on 14-2-25.
 */
@Service
public class AccountPermissionServiceImpl implements AccountPermissionService {

    @Autowired
    private AccountPermissionMapper accountPermissionMapper;

    @Override
    public void save(@Param("pojo") AccountPermission o) {
        accountPermissionMapper.save(o);
    }

    @Override
    public void update(@Param("pojo") AccountPermission o) {
        accountPermissionMapper.update(o);
    }

    @Override
    public void deleteByIds(@Param("ids") Integer[] ids) {
        accountPermissionMapper.deleteByIds(ids);
    }

    @Override
    public List<AccountPermission> findByIds(@Param("ids") Integer[] ids) {
        return accountPermissionMapper.findByIds(ids);
    }

    @Override
    public AccountPermission findById(@Param("id") Integer id) {
        return accountPermissionMapper.findById(id);
    }

    @Override
    public List<AccountPermission> findAll() {
        return accountPermissionMapper.findAll();
    }

    @Override
    public List<AccountPermission> findPermissionsByGroupId(@Param("groupId") Integer groupId) {
        return accountPermissionMapper.findPermissionsByGroupId(groupId);
    }
}
