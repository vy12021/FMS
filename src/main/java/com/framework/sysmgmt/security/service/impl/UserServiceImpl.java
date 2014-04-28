package com.framework.sysmgmt.security.service.impl;

import com.framework.sysmgmt.security.dao.UserMapper;
import com.framework.sysmgmt.security.model.User;
import com.framework.sysmgmt.security.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by LIUYONG on 14-1-31.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 以用户名查找用户
     */
    @Override
    public User findByUsername(@Param("username") String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public void save(@Param("pojo") User o) {
        userMapper.save(o);
    }

    @Override
    public void update(@Param("pojo") User o) {
        userMapper.update(o);
    }

    @Override
    public void deleteByIds(@Param("ids") Integer[] ids) {
        userMapper.deleteByIds(ids);
    }

    @Override
    public List<User> findByIds(@Param("ids") Integer[] ids) {
        return userMapper.findByIds(ids);
    }

    @Override
    public User findById(@Param("id") Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }
}
