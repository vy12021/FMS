package com.vy12021.framework.sysmgmt.security.dao;

import com.vy12021.framework.BaseMapper;
import com.vy12021.framework.sysmgmt.security.model.AccountPermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by LIUYONG on 14-2-25.
 */
@Repository
public interface AccountPermissionMapper extends BaseMapper<AccountPermission> {

    public List<AccountPermission> findPermissionsByGroupId(@Param("groupId") Integer groupId);

}