package com.xmu.cms.dao;

import com.xmu.cms.entity.Team;

import java.util.List;

/**
 * @author JuboYu on 2018/12/16.
 * @version 1.0
 */
public interface TeamDao {
    List<Team> getTeamInCourse(Integer courseId);

    Team getTeamInCourseByStudent(Integer courseId, Integer studentId);
}
