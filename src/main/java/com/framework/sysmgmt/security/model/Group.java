package com.framework.sysmgmt.security.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LIUYONG on 14-2-22.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Group implements Serializable {

    private static final long serialVersionUID = -3941376174127157779L;
    /**
     * 组(角色)id
     */
    private Integer id;

    /**
     * 组名
     */
    private String groupName;

    /**
     * 组描述信息
     */
    private String description;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 创建人
     **/
    private String created;

    /**
     * 权限列表
     */
    private List<AccountPermission> accountPermissionList;

}
