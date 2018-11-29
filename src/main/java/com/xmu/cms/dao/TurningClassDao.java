package com.xmu.cms.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@Mapper
public interface TurningClassDao {
    Integer newTurningClass(Integer courseId, String name, String classTime, String classPlace);
}
