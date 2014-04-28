package com.framework;

import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by LIUYONG on 14-1-31.
 */
public interface BaseMapper<T> {

    public void save(@Param("pojo")T o);

    public void update(@Param("pojo")T o);

    public void deleteByIds(@Param("ids")Integer[] ids);

    public List<T> findByIds(@Param("ids")Integer[] ids);

    public T findById(@Param("id")Integer id);

    public List<T> findAll();

}
