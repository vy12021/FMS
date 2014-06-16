package com.vy12021.framework.sysmgmt.security.service;

import com.vy12021.framework.BaseService;
import com.vy12021.framework.sysmgmt.security.dao.GroupMapper;
import com.vy12021.framework.sysmgmt.security.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LIUYONG on 14-2-25.
 */
@Service
public class GroupService extends BaseService<Group, Long, GroupMapper> {

    @Autowired
    @Override
    public void setMapper(GroupMapper mapper) {
        super.setMapper(mapper);
    }


}
