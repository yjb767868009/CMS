package com.xmu.cms.dao;

import com.xmu.cms.entity.*;
import com.xmu.cms.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JuboYu on 2018/12/11.
 * @version 1.0
 */
@Component
public class CourseDao {
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private KlassMapper klassMapper;

    @Autowired
    private RoundMapper roundMapper;

    @Autowired
    private SeminarMapper seminarMapper;

    @Autowired
    private KlassSeminarMapper klassSeminarMapper;

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private ShareSeminarMapper shareSeminarMapper;

    @Autowired
    private ShareTeamMapper shareTeamMapper;

    @Autowired
    private SeminarScoreMapper seminarScoreMapper;

    public List<Course> getAllCoursesByTeacherId(BigInteger teacherId) {
        return courseMapper.getAllCourseByTeacherId(teacherId);
    }

    public void deleteCourse(BigInteger courseId) {
        courseMapper.deleteCourseById(courseId);
        List<Klass> klasses = klassMapper.getKlassesInCourse(courseId);
        for (Klass klass : klasses) {
            klassMapper.deleteKlassByKlassId(klass.getKlassId());
        }
        List<Round> rounds = roundMapper.getRoundsByCourseId(courseId);
        for (Round round : rounds) {
            roundMapper.deleteRoundByRoundId(round.getRoundId());
        }
        List<Seminar> seminars = seminarMapper.getAllSeminarByCourseId(courseId);
        for (Seminar seminar : seminars) {
            seminarMapper.deleteSeminar(seminar.getSeminarId());
        }
        List<KlassSeminar> klassSeminars = new ArrayList<KlassSeminar>();
        for (Klass klass : klasses) {
            for (Seminar seminar : seminars) {
                klassSeminars.add(klassSeminarMapper.getKlassSeminarByKlassAndSeminar(klass.getKlassId(), seminar.getSeminarId()));
            }
        }
        for (KlassSeminar klassSeminar : klassSeminars) {
            klassSeminarMapper.deleteKlassSeminar(klassSeminar.getKlassSeminarId());
        }
        for (Klass klass : klasses) {
            for (Round round : rounds) {
                klassMapper.deleteKlassRound(klass.getKlassId(), round.getRoundId());
            }
        }
        for (Klass klass : klasses) {
            klassMapper.deleteKlassStudent(klass.getKlassId());
        }
        for (Round round : rounds) {
            roundMapper.deleteRoundScore(round.getRoundId());
        }
        for (Seminar seminar : seminars) {
            seminarScoreMapper.deleteSeminarScore(seminar.getSeminarId());
        }
        List<Team> teams = teamMapper.getTeamInCourse(courseId);
        for (Team team : teams) {
            teamMapper.deleteTeamByTeamId(team.getTeamId());
            shareSeminarMapper.deleteShareSeminarByCourseId(courseId);
            shareTeamMapper.deleteShareTeamByCourseId(courseId);
        }
    }

    public List<Course> getAllCourseByStudentId(BigInteger studentId) {
        return courseMapper.getAllCourseByStudentId(studentId);
    }

    public Integer createCourse(Course course) {
        return courseMapper.createCourse(course);
    }

    public Course getCourse(BigInteger courseId) {
        return courseMapper.getCourseById(courseId);
    }

    public void updateCourseTeamMainCourse(Course course) {
        courseMapper.updateCourseTeamMainCourse(course);
        courseMapper.deleteCourseTeam(course);
    }

    public void updateCourseSeminarMainCourse(Course course) {
        courseMapper.updateCourseSeminarMainCourse(course);
    }

    public Course getTeamMainCourse(BigInteger courseId) {
        return courseMapper.getTeamMainCourseByCourseId(courseId);
    }

    public Course getSeminarMainCourse(BigInteger courseId) {
        return courseMapper.getSeminarMainCourseByCourseId(courseId);
    }
}
