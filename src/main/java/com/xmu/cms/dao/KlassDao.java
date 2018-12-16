package com.xmu.cms.dao;

import com.xmu.cms.entity.Klass;

import java.util.List;

/**
 * @author JuboYu on 2018/12/16.
 * @version 1.0
 */
public interface KlassDao {
    Integer newKlass(Integer courseId, Klass klass);

    List<Klass> getAllKlass(Integer courseId);
}
