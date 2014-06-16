package com.vy12021.framework.sysmgmt.security.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by LIUYONG on 14-2-23.
 * 用户详细信息
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -4404442228678992315L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户关联id
     */
    private Long userId;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private String age;

    /**
     * email
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

}
