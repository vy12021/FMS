package com.vy12021.framework.sysmgmt.security.dao;

import com.vy12021.framework.BaseMapper;
import com.vy12021.framework.sysmgmt.security.model.User;
import com.vy12021.framework.util.paging.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LIUYONG on 14-1-31.
 */

@Repository
public interface UserMapper extends BaseMapper<User, Serializable> {

   public User findByUsername(@Param("username")String username);

   public List<User> findPager(@Param("pager")Pager pager);

}
