package com.xmu.cms.mapper;

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

    /**
     * 开始讨论课
     *
     * @param klassSeminarId 班级讨论课id
     * @return 开始成功信息
     */
    Integer startKlassSeminar(@Param("klassSeminarId") BigInteger klassSeminarId);

    /**
     * 暂停讨论课
     *
     * @param klassSeminarId 班级讨论课id
     * @return 暂停成功信息
     */
    Integer stopKlassSeminar(@Param("klassSeminarId") BigInteger klassSeminarId);

    /**
     * 结束讨论课
     *
     * @param klassSeminarId 班级讨论课id
     * @return 结束成功信息
     */
    Integer endKlassSeminar(@Param("klassSeminarId") BigInteger klassSeminarId);

    /**
     * 获取讨论课信息
     *
     * @param seminarId 讨论课id
     * @return 讨论课
     */
    Seminar getSeminarBySeminarId(@Param("seminarId") BigInteger seminarId);

    /**
     * 获取轮次中的讨论课
     *
     * @param roundId 轮次id
     * @return 讨论课列表
     */
    List<Seminar> getAllSeminarByRoundId(@Param("roundId") BigInteger roundId);

    /**
     * 获取教师下正在进行的讨论课
     *
     * @param teacherId 教师id
     * @return 讨论课
     */
    Seminar getRunningSeminarByTeacherId(@Param("teacherId") BigInteger teacherId);

    /**
     * 删除讨论课
     *
     * @param seminarId 讨论课id
     * @return 删除讨论课信息
     */
    Integer deleteSeminar(@Param("seminarId") BigInteger seminarId);

    /**
     * 获取课程中的所有讨论课
     *
     * @param courseId 课程id
     * @return 讨论课列表
     */
    List<Seminar> getAllSeminarByCourseId(@Param("courseId") BigInteger courseId);

}
