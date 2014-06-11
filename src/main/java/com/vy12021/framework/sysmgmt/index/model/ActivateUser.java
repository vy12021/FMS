package com.vy12021.framework.sysmgmt.index.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by LIUYONG on 14-2-7.
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class ActivateUser implements Serializable {

    private static final long serialVersionUID = -1541047194880389364L;

    private Integer id;

    private String activateId;

    private Integer userId;

}
