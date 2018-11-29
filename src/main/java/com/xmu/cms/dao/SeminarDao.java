package com.xmu.cms.dao;

import com.xmu.cms.entity.Seminar;
import com.xmu.cms.entity.Team;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
@Mapper
public interface SeminarDao {
    List<Seminar> getAllSeminarByCourseId(@Param("courseId") Integer courseId);

    Seminar getSeminarById(@Param("seminarId") Integer seminarId);

    String newSeminar(@Param("courseId") Integer courseId,
                      @Param("topic") String topic,
                      @Param("introduction") String introduction,
                      @Param("visible") Boolean visible);

    String modifySeminar(@Param("courseId") Integer courseId,
                         @Param("seminarId") Integer seminarId,
                         @Param("topic") String topic,
                         @Param("introduction") String introduction,
                         @Param("visible") Boolean visible);
}
