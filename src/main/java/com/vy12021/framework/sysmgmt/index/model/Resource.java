package com.vy12021.framework.sysmgmt.index.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by LIUYONG on 14-2-9.
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class Resource implements Serializable {

    private static final long serialVersionUID = 2165670062464092725L;
    private Integer id;

    private String name;

    private String uri;

    private String resourceUUID;

    private String desFmt;

    private String size;

    private String type;

    private String createUser;

    private String createDate;

    private String convertStatus;

}
