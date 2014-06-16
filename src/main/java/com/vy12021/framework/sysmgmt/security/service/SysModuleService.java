package com.vy12021.framework.sysmgmt.security.service;

import com.vy12021.framework.BaseService;
import com.vy12021.framework.sysmgmt.security.dao.AccountPermissionMapper;
import com.vy12021.framework.sysmgmt.security.dao.SysModuleMapper;
import com.vy12021.framework.sysmgmt.security.model.AccountPermission;
import com.vy12021.framework.sysmgmt.security.model.SysModule;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LIUYONG on 14-2-25.
 */
@Service
public class SysModuleService extends BaseService<SysModule, Long, SysModuleMapper> {

    @Autowired
    @Override
    public void setMapper(SysModuleMapper mapper) {
        super.setMapper(mapper);
    }

    @Transactional
    public List<SysModule> findModulesByUser(@Param("userId")Long userId) {
        return this.getMapper().findModulesByUser(userId);
    }

    @Transactional
    public List<SysModule> findModulesBySuperId(@Param("userId")Long userId, @Param("superId")Long superId) {
        return this.getMapper().findModulesBySuperId(userId, superId);
    }

    @Transactional
    public Map<String, Object> tree(Long superId) {
        Map<String, Object> treeMap = new LinkedHashMap<String, Object>();
        List<SysModule> sysModuleList = findModulesBySuperId((Long)SecurityUtils.getSubject().getSession().getAttribute("userId"), superId);
        for (SysModule sysModule : sysModuleList) {
            treeMap.put(sysModule.getModuleName(), sysModule);
            if (sysModule.getIsLeave() == 0) {
                sysModuleList = findModulesBySuperId((Long)SecurityUtils.getSubject().getSession().getAttribute("userId"), superId);
            }
        }
        return treeMap;
    }

}