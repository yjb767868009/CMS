package com.xmu.cms.dao;

import com.xmu.cms.entity.Seminar;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
public interface SeminarDao {
    List<Seminar> getAllSeminarByCourseId(@Param("courseId") Integer courseId);
}
