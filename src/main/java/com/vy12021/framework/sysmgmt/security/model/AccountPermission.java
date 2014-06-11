package com.vy12021.framework.sysmgmt.security.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by LIUYONG on 14-2-22.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AccountPermission implements Serializable {

    private static final long serialVersionUID = -2059161707922559592L;
    /**
     * 权限id
     */
    private Integer id;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限描述
     */
    private String description;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 创建人
     */
    private String created;

}
