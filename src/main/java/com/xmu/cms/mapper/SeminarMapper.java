package com.xmu.cms.mapper;

import com.xmu.cms.entity.Seminar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@Mapper
public interface SeminarMapper {
    /**
     * 新建讨论课
     *
     * @param seminar 新建的讨论课
     * @return 新建讨论课信息
     */
    Integer insertSeminar(@Param("seminar") Seminar seminar);

    /**
     * 修改讨论课
     *
     * @param seminar 修改的讨论课
     * @return 修改讨论课的信息
     */
    Integer modifySeminar(@Param("seminar") Seminar seminar);


    Integer startKlassSeminar(@Param("klassSeminarId") Integer klassSeminarId);

    Integer stopKlassSeminar(@Param("klassSeminarId") Integer klassSeminarId);

    Integer endKlassSeminar(@Param("klassSeminarId") Integer klassSeminarId);

    Integer getAttendanceNo(@Param("seminarId") Integer seminarId);

    Seminar getSeminarBySeminarId(@Param("seminarId") Integer seminarId);

    List<Seminar> getAllSeminarByRoundId(@Param("roundId") Integer roundId);

    Seminar getRunningSeminarByTeacherId(@Param("teacherId") Integer teacherId);

    /**
     * 删除讨论课
     *
     * @param seminarId 讨论课id
     * @return 删除讨论课信息
     */
    Integer deleteSeminar(@Param("seminarId") Integer seminarId);

    /**
     * 获取课程中的所有讨论课
     *
     * @param courseId 课程id
     * @return 讨论课列表
     */
    List<Seminar> getAllSeminarByCourseId(@Param("courseId") Integer courseId);
}
