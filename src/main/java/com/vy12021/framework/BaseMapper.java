package com.vy12021.framework;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LIUYONG on 14-1-31.
 */
public interface BaseMapper<T, PK extends Serializable> {

    public void save(@Param("pojo")T o);

    public void update(@Param("pojo")T o);

    public void deleteByIds(@Param("ids")PK[] ids);

    public List<T> findByIds(@Param("ids")PK[] ids);

    public T findById(@Param("id")PK id);

    public List<T> findAll();

}
