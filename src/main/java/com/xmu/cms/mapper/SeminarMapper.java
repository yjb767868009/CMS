package com.xmu.cms.mapper;

import com.xmu.cms.entity.KlassSeminar;
import com.xmu.cms.entity.Seminar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
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


    Integer startKlassSeminar(@Param("klassSeminarId") BigInteger klassSeminarId);

    Integer stopKlassSeminar(@Param("klassSeminarId") BigInteger klassSeminarId);

    Integer endKlassSeminar(@Param("klassSeminarId") BigInteger klassSeminarId);

    Integer getAttendanceNo(@Param("seminarId") BigInteger seminarId);

    Seminar getSeminarBySeminarId(@Param("seminarId") BigInteger seminarId);

    List<Seminar> getAllSeminarByRoundId(@Param("roundId") BigInteger roundId);

    KlassSeminar getRunningKlassSeminarByTeacherId(@Param("teacherId") BigInteger teacherId);

    /**
     * 删除讨论课
     *
     * @param seminarId 讨论课id
     * @return 删除讨论课信息
     */
    Integer deleteSeminar(@Param("seminarId") BigInteger seminarId);

    /**
     * 修改讨论课的报告截止时间
     *
     * @param seminar 修改的后的讨论课
     * @return 修改讨论课的信息
     */
    Integer modifySeminarReportDDL(Seminar seminar);

    /**
     * 获取课程中的所有讨论课
     *
     * @param courseId 课程id
     * @return 讨论课列表
     */
    List<Seminar> getAllSeminarByCourseId(@Param("courseId") BigInteger courseId);

}
