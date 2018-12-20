package com.xmu.cms.mapper;

import com.xmu.cms.entity.Team;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
@Mapper
public interface TeamMapper {

    List<Team> getAllTeamsInSeminar(@Param("seminarId") BigInteger seminarId);

    List<Team> getAllTeamsInKlassSeminar(@Param("seminarId") BigInteger klassSeminarId);

    Team getTeamByTeamId(BigInteger teamId);

    Team getTeamInKlassByStudentId(BigInteger klassId, BigInteger studentId);

    List<Team> getTeamInCourse(BigInteger courseId);

    /**
     * 获取课程中该学生的队伍
     *
     * @param courseId  课程id
     * @param studentId 学生id
     * @return 队伍信息
     */
    Team getTeamInCourseByStudent(@Param("courseId") BigInteger courseId,
                                  @Param("studentId") BigInteger studentId);

    /**
     * 删除队伍
     *
     * @param teamId 队伍id
     * @return 删除信息
     */
    Integer deleteTeamByTeamId(BigInteger teamId);

    /**
     * 新建队伍（不包括新建队伍学生关系），默认status为0
     *
     * @param klassId   班级id
     * @param courseId  课程id
     * @param studentId 队长id
     * @param team      队伍信息
     * @return
     */
    Integer insertTeam(BigInteger klassId, BigInteger courseId, BigInteger studentId, Team team);

    /**
     * 根据班级和队长获取队伍
     *
     * @param klassId   班级id
     * @param studentId 学生id
     * @return 队伍信息
     */
    Team getTeamByKlass(BigInteger klassId, BigInteger studentId);
}
