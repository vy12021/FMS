package com.vy12021.framework;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liuyong on 6/15/14.
 */
public abstract class BaseService <T, PK extends Serializable, Mapper extends BaseMapper<T, Serializable>> {

    private Mapper mapper;


    public Mapper getMapper() {
        return mapper;
    }

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    public void save(@Param("pojo")T o) {
        mapper.save(o);
    }

    public void update(@Param("pojo")T o) {
        mapper.update(o);
    }

    public void deleteByIds(@Param("ids")PK[] ids) {
        mapper.deleteByIds(ids);
    }

    public List<T> findByIds(@Param("ids")PK[] ids) {
        return mapper.findByIds(ids);
    }

    public T findById(@Param("id")PK id) {
        return mapper.findById(id);
    }

    public List<T> findAll() {
        return mapper.findAll();
    }

}
