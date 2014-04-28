package com.framework.sysmgmt.security.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LIUYONG on 14-1-14.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 7952603493848682521L;
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 口令
     */
    private String password;

    /**
     * 激活状态
     */
    private String activateStatus;

    private String createDate;

    private Integer created;
    /**
     * 用户详细信息
     */
    private UserInfo userInfo;

    /**
     * 用户组列表
     */
    private List<Group> groupList;

}
