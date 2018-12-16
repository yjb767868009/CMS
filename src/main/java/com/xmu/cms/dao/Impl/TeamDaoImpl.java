package com.xmu.cms.dao.Impl;

import com.xmu.cms.dao.TeamDao;
import com.xmu.cms.entity.Course;
import com.xmu.cms.entity.Student;
import com.xmu.cms.entity.Team;
import com.xmu.cms.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author JuboYu on 2018/12/16.
 * @version 1.0
 */
@Component
public class TeamDaoImpl implements TeamDao {

    @Autowired
    private TeamMapper teamMapper;

    @Override
    public List<Team> getTeamInCourse(Course course) {
        return teamMapper.getTeamInCourse(course);
    }

    @Override
    public Team getTeamInCourseByStudent(Course course, Student student) {
        return teamMapper.getTeamInCourseByStudent(course, student.getStudentId());
    }
}
