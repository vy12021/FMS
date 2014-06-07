package com.framework.sysmgmt.security.dao;

import com.framework.BaseMapper;
import com.framework.sysmgmt.security.model.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by LIUYONG on 14-2-25.
 */
@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    UserInfo findByEmail(@Param("email")String email);

    UserInfo findByUserId(@Param("userId")Integer userId);

}
