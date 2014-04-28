package com.framework.sysmgmt.security.service.impl;

import com.framework.sysmgmt.security.dao.GroupMapper;
import com.framework.sysmgmt.security.model.Group;
import com.framework.sysmgmt.security.model.AccountPermission;
import com.framework.sysmgmt.security.service.GroupService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by LIUYONG on 14-2-25.
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;


    @Override
    public void save(@Param("pojo") Group o) {
        groupMapper.save(o);
    }

    @Override
    public void update(@Param("pojo") Group o) {
        groupMapper.update(o);
    }

    @Override
    public void deleteByIds(@Param("ids") Integer[] ids) {
        groupMapper.deleteByIds(ids);
    }

    @Override
    public List<Group> findByIds(@Param("ids") Integer[] ids) {
        return groupMapper.findByIds(ids);
    }

    @Override
    public Group findById(@Param("id") Integer id) {
        return groupMapper.findById(id);
    }

    @Override
    public List<Group> findAll() {
        return groupMapper.findAll();
    }

}
