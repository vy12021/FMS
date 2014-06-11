package com.vy12021.framework.sysmgmt.security.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by LIUYONG on 14-3-7.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysModule implements Serializable {

    private static final long serialVersionUID = 8065075723148790668L;

    /*主键*/
    private Integer id;

    /*父模块id*/
    private Integer superId;

    /*模块名称*/
    private String moduleName;

    /*模块链接*/
    private String moduleUrl;

    /*模块的资源名称*/
    private String resourceName;

    /*模块图标*/
    private String iconUri;

    /*是否叶子节点*/
    private String isLeave;

    /*授权id*/
    private String authId;
}
