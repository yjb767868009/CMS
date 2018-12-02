package com.xmu.cms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@Mapper
public interface ClbumDao {
    Integer newClbum(@Param("courseId") Integer courseId,
                     @Param("name") String name,
                     @Param("classTime") String classTime,
                     @Param("classPlace") String classPlace);
}
