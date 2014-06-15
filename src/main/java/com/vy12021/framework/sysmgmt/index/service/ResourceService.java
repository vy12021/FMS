package com.vy12021.framework.sysmgmt.index.service;

import com.vy12021.framework.BaseService;
import com.vy12021.framework.sysmgmt.index.dao.ResourceMapper;
import com.vy12021.framework.sysmgmt.index.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LIUYONG on 14-2-9.
 */

@Service
public class ResourceService extends BaseService<Resource, Integer, ResourceMapper> {

    @Autowired
    @Override
    public void setMapper(ResourceMapper mapper) {
        super.setMapper(mapper);
    }
}
