package com.vy12021.framework.sysmgmt.index.dao;

import com.vy12021.framework.sysmgmt.index.model.ActivateUser;
import com.vy12021.framework.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by LIUYONG on 14-2-7.
 */

@Repository
public interface ActivateMapper extends BaseMapper<ActivateUser, Serializable> {

    public ActivateUser findByUserId(@Param("userId")Serializable userId);

    public ActivateUser findByActivateId(@Param("activateId")String activateId);

}
