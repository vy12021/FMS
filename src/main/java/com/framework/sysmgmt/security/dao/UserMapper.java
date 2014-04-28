package com.framework.sysmgmt.security.dao;

import com.framework.BaseMapper;
import com.framework.sysmgmt.security.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by LIUYONG on 14-1-31.
 */

@Repository
public interface UserMapper extends BaseMapper<User> {

   public User findByUsername(@Param("username")String username);

}
