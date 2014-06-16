package com.vy12021.framework.sysmgmt.security.service;

import com.vy12021.framework.BaseService;
import com.vy12021.framework.sysmgmt.security.dao.UserInfoMapper;
import com.vy12021.framework.sysmgmt.security.model.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by LIUYONG on 14-2-25.
 */
@Service
public class UserInfoService extends BaseService<UserInfo, Long, UserInfoMapper> {

    @Autowired
    @Override
    public void setMapper(UserInfoMapper mapper) {
        super.setMapper(mapper);
    }

    public UserInfo findByEmail(@Param("email")String email) {
        return this.getMapper().findByEmail(email);
    }

    public UserInfo findByUserId(@Param("userId")Long userId) {
        return this.getMapper().findByUserId(userId);
    }
}
