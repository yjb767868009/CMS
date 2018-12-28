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
    /**
     * 获取讨论课中所有队伍
     *
     * @param seminarId 讨论课id
     * @return 队伍列表
     */
    List<Team> getAllTeamsInSeminar(@Param("seminarId") BigInteger seminarId);

    /**
     * 获取班级讨论课中所有队伍
     *
     * @param klassSeminarId 班级讨论课id
     * @return 队伍列表
     */
    List<Team> getAllTeamsInKlassSeminar(@Param("seminarId") BigInteger klassSeminarId);

    /**
     * 获取队伍信息
     *
     * @param teamId 队伍id
     * @return 队伍列表
     */
    Team getTeamByTeamId(@Param("teamId") BigInteger teamId);

    /**
     * 获取学生在班级中的队伍
     *
     * @param klassId   班级id
     * @param studentId 学生id
     * @return 队伍信息
     */
    Team getTeamInKlassByStudentId(@Param("klassId") BigInteger klassId,
                                   @Param("studentId") BigInteger studentId);

    /**
     * 获取课程中队伍
     *
     * @param courseId 课程id
     * @return 队伍列表
     */
    List<Team> getTeamInCourse(@Param("courseId") BigInteger courseId);

    /**
     * 删除队伍
     *
     * @param teamId 队伍id
     * @return 删除信息
     */
    Integer deleteTeamByTeamId(@Param("teamId") BigInteger teamId);

    /**
     * 新建队伍（不包括新建队伍学生关系），默认status为0
     *
     * @param klassId   班级id
     * @param courseId  课程id
     * @param studentId 队长id
     * @param team      队伍信息
     * @return 新建成功信息
     */
    Integer insertTeam(@Param("klassId") BigInteger klassId,
                       @Param("courseId") BigInteger courseId,
                       @Param("studentId") BigInteger studentId,
                       @Param("team") Team team);

    /**
     * 根据班级和队长获取队伍
     *
     * @param klassId   班级id
     * @param studentId 学生id
     * @return 队伍信息
     */
    Team getTeamByKlass(@Param("klassId") BigInteger klassId, @Param("studentId") BigInteger studentId);

    /**
     * 更新队伍合法性
     *
     * @param team 队伍信息
     */
    void updateTeamValid(@Param("team") Team team);

    /**
     * 学生在某节班级讨论课属于的队伍
     *
     * @param studentId      学生id
     * @param klassSeminarId 班级讨论课id
     * @return 队伍
     */
    Team getStudentTeamInKlassSeminar(@Param("studentId") BigInteger studentId,
                                      @Param("klassSeminarId") BigInteger klassSeminarId);

    /**
     * 获取班级中学生的队伍
     *
     * @param studentId 学生id
     * @param klassId   班级id
     * @return 队伍
     */
    Team getStudentTeamInKlass(@Param("studentId") BigInteger studentId,@Param("klassId") BigInteger klassId);

    /**
     * 获取简单的队伍信息，无嵌套关系
     *
     * @param courseId 课程id
     * @return 队伍列表
     */
    List<Team> getSimpleTeamInCourse(@Param("courseId") BigInteger courseId);

    /**
     * 获取学生在课程下的队伍
     *
     * @param studentId 学生id
     * @param courseId  课程id
     * @return 队伍
     */
    Team getStudentTeamInCourse(@Param("studentId") BigInteger studentId,
                                @Param("courseId") BigInteger courseId);

    /**
     * 获取班级里最后的队伍
     * @param klassId 班级id
     * @return 队伍
     */
    Team getLastTeamInKlass(BigInteger klassId);// TODO: 2018/12/28  
}
