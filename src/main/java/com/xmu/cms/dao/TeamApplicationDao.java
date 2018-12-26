package com.xmu.cms.dao;

import com.xmu.cms.entity.Course;
import com.xmu.cms.entity.TeamApplication;
import com.xmu.cms.mapper.CourseMapper;
import com.xmu.cms.mapper.TeamApplicationMapper;
import com.xmu.cms.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/23.
 * @version 1.0
 */
@Component
public class TeamApplicationDao{
    @Autowired
    private TeamApplicationMapper teamApplicationMapper;

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private CourseMapper courseMapper;

    public List<TeamApplication> getTeamApplicationByTeacherId(BigInteger teacherId) {
        return teamApplicationMapper.getTeamApplicationByTeacherId(teacherId);
    }

    public TeamApplication updateTeamApplication(TeamApplication teamApplication) {
        teamApplicationMapper.updateTeamApplication(teamApplication);
        return teamApplicationMapper.getTeamApplication(teamApplication.getTeamApplicationId());
    }

    public TeamApplication sendTeamApplication(TeamApplication teamApplication) {
        Course course = courseMapper.getCourseByTeamId(teamApplication.getTeam().getTeamId());
        teamApplicationMapper.insertTeamApplication(teamApplication);
        return teamApplicationMapper.getTeamApplicationByTeamId(teamApplication.getTeam().getTeamId());
    }
}
