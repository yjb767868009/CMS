package com.xmu.cms.dao;

import com.xmu.cms.entity.Course;
import com.xmu.cms.entity.Teacher;
import com.xmu.cms.entity.Team;
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
public class TeamApplicationDao {
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

    public TeamApplication sendTeamApplication(TeamApplication teamApplication) throws Exception {
        Team team = teamApplication.getTeam();
        Course course = courseMapper.getCourseByTeamId(team.getTeamId());
        Teacher teacher = course.getTeacher();
        if (teacher == null) {
            throw new Exception("无效的组队申请");
        }
        teamApplication.setTeacher(course.getTeacher());
        TeamApplication findTeamApplication = teamApplicationMapper.getTeamApplicationByTeamId(teamApplication.getTeam().getTeamId());
        if (findTeamApplication != null) {
            throw new Exception("请勿重复申请");
        }
        teamApplicationMapper.insertTeamApplication(teamApplication);
        teamApplication = teamApplicationMapper.getTeamApplicationByTeamId(team.getTeamId());
        team = teamMapper.getTeamByTeamId(team.getTeamId());
        teamApplication.setTeam(team);
        return teamApplication;
    }
}
