package com.xmu.cms.mapper;

import com.xmu.cms.entity.ConflictCourse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author JuboYu on 2018/12/20.
 * @version 1.0
 */
@Mapper
public interface ConflictCourseMapper {
    ConflictCourse getConflictCourse(@Param("conflictCourseId") Integer conflictCourseId);

    Integer newConflictCourse(@Param("course1Id") Integer course1Id,
                              @Param("course2Id") Integer course2Id);

    Integer deleteConflictCourse(@Param("conflictCourseId") Integer conflictCourseId);
}
