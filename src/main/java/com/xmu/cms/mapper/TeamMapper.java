package com.xmu.cms.mapper;

import com.xmu.cms.entity.Team;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
@Mapper
public interface TeamMapper {

    List<Team> getAllTeamsInSeminar(@Param("seminarId") Integer seminarId);

    List<Team> getAllTeamsInKlassSeminar(@Param("seminarId") Integer klassSeminarId);

    Team getTeamByTeamId(Integer teamId);

    Team getTeamInKlassByStudentId(Integer klassId, Integer studentId);

    List<Team> getTeamInCourse(Integer courseId);

    /**
     * 获取课程中该学生的队伍
     * @param courseId 课程id
     * @param studentId 学生id
     * @return 队伍信息
     */
    Team getTeamInCourseByStudent(@Param("courseId") Integer courseId,
                                  @Param("studentId") Integer studentId);
}
