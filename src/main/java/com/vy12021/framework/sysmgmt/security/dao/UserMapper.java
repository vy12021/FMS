package com.vy12021.framework.sysmgmt.security.dao;

import com.vy12021.framework.BaseMapper;
import com.vy12021.framework.sysmgmt.security.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by LIUYONG on 14-1-31.
 */

@Repository
public interface UserMapper extends BaseMapper<User> {

   public User findByUsername(@Param("username")String username);

}
