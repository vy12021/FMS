package com.vy12021.framework.sysmgmt.security.dao;

import com.vy12021.framework.BaseMapper;
import com.vy12021.framework.sysmgmt.security.model.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by LIUYONG on 14-2-25.
 */
@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo, Serializable> {

    UserInfo findByEmail(@Param("email")String email);

    UserInfo findByUserId(@Param("userId")Integer userId);

}
