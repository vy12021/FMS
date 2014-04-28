package com.springapp.mvc.mybatis.service.impl;

import com.springapp.mvc.model.Resource;
import com.springapp.mvc.mybatis.persistence.ResourceMapper;
import com.springapp.mvc.mybatis.service.ResourceService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LIUYONG on 14-2-9.
 */

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public void save(@Param("pojo") Resource o) {
        resourceMapper.save(o);
    }

    @Override
    public void update(@Param("pojo") Resource o) {
        resourceMapper.update(o);
    }

    @Override
    public void deleteByIds(@Param("ids") Integer[] ids) {
        resourceMapper.deleteByIds(ids);
    }

    @Override
    public List<Resource> findByIds(@Param("ids") Integer[] ids) {
        return resourceMapper.findByIds(ids);
    }

    @Override
    public Resource findById(@Param("id") Integer id) {
        return resourceMapper.findById(id);
    }

    @Override
    public List<Resource> findAll() {
        return resourceMapper.findAll();
    }
}
