package com.framework.sysmgmt.security.dao;

import com.framework.BaseMapper;
import com.framework.sysmgmt.security.model.AccountPermission;
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
