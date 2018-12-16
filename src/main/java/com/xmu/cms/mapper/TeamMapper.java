package com.xmu.cms.mapper;

import com.xmu.cms.entity.Course;
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

    List<Team> getTeamInCourse(Course course);

    Team getTeamInCourseByStudent(Course course, Integer studentId);
}
