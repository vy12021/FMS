package com.vy12021.framework.sysmgmt.security.dao;

import com.vy12021.framework.BaseMapper;
import com.vy12021.framework.sysmgmt.security.model.SysModule;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LIUYONG on 14-2-25.
 */
@Repository
public interface SysModuleMapper extends BaseMapper<SysModule, Serializable> {

    public List<SysModule> findModulesByUser(@Param("userId")Long userId);

    public List<SysModule> findModulesBySuperId(@Param("userId")Long userId, @Param("superId")Long superId);

}
