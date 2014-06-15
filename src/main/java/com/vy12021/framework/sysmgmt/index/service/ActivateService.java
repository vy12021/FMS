package com.vy12021.framework.sysmgmt.index.service;

import com.vy12021.framework.BaseService;
import com.vy12021.framework.sysmgmt.index.dao.ActivateMapper;
import com.vy12021.framework.sysmgmt.index.model.ActivateUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by LIUYONG on 14-2-7.
 */
@Service
public class ActivateService extends BaseService<ActivateUser, Integer, ActivateMapper> {

    @Autowired
    @Override
    public void setMapper(ActivateMapper mapper) {
        super.setMapper(mapper);
    }

    public ActivateUser findByUserId(@Param("userId")Serializable userId) {
        return this.getMapper().findByUserId(userId);
    }

    public ActivateUser findByActivateId(@Param("activateId")String activateId) {
        return this.getMapper().findByActivateId(activateId);
    }

}
