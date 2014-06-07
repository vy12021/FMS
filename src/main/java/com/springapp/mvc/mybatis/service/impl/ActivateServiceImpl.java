package com.springapp.mvc.mybatis.service.impl;

import com.springapp.mvc.model.ActivateUser;
import com.springapp.mvc.mybatis.persistence.ActivateMapper;
import com.springapp.mvc.mybatis.service.ActivateService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LIUYONG on 14-2-7.
 */

@Service
public class ActivateServiceImpl implements ActivateService {

    @Autowired
    private ActivateMapper activateMapper;

    @Override
    public ActivateUser findByUserId(Integer userId) {
        return activateMapper.findByUserId(userId);
    }

    @Override
    public ActivateUser findByActivateId(@Param("activateId") String activateId) {
        return activateMapper.findByActivateId(activateId);
    }

    @Override
    public void save(@Param("pojo") ActivateUser o) {
        activateMapper.save(o);
    }

    @Override
    public void update(@Param("pojo") ActivateUser o) {
        activateMapper.update(o);
    }

    @Override
    public void deleteByIds(@Param("ids") Integer[] ids) {
        activateMapper.deleteByIds(ids);
    }

    @Override
    public List<ActivateUser> findByIds(@Param("ids") Integer[] ids) {
        return activateMapper.findByIds(ids);
    }

    @Override
    public ActivateUser findById(@Param("id") Integer id) {
        return activateMapper.findById(id);
    }

    @Override
    public List<ActivateUser> findAll() {
        return activateMapper.findAll();
    }
}
