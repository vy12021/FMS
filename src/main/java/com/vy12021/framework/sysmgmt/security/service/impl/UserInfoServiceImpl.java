package com.vy12021.framework.sysmgmt.security.service.impl;

import com.vy12021.framework.sysmgmt.security.dao.UserInfoMapper;
import com.vy12021.framework.sysmgmt.security.model.UserInfo;
import com.vy12021.framework.sysmgmt.security.service.UserInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by LIUYONG on 14-2-25.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public void save(@Param("pojo") UserInfo o) {
        userInfoMapper.save(o);
    }

    @Override
    public void update(@Param("pojo") UserInfo o) {
        userInfoMapper.update(o);
    }

    @Override
    public void deleteByIds(@Param("ids") Integer[] ids) {
        userInfoMapper.deleteByIds(ids);
    }

    @Override
    public List<UserInfo> findByIds(@Param("ids") Integer[] ids) {
        return userInfoMapper.findByIds(ids);
    }

    @Override
    public UserInfo findById(@Param("id") Integer id) {
        return userInfoMapper.findById(id);
    }

    @Override
    public List<UserInfo> findAll() {
        return userInfoMapper.findAll();
    }

    @Override
    public UserInfo findByEmail(@Param("email") String email) {
        return userInfoMapper.findByEmail(email);
    }

    @Override
    public UserInfo findByUserId(@Param("userId") Integer userId) {
        return userInfoMapper.findByUserId(userId);
    }
}
