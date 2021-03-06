package com.xmu.cms.service;

import com.xmu.cms.dao.*;
import com.xmu.cms.entity.*;
import com.xmu.cms.entity.strategy.Strategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
@Service
public class CourseService {
    private String noCourseError = "无此课程";

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private RoundDao roundDao;

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private KlassDao klassDao;

    @Autowired
    private ShareTeamDao shareTeamDao;

    @Autowired
    private ShareSeminarDao shareSeminarDao;

    @Autowired
    private TeamApplicationDao teamApplicationDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private StrategyDao strategyDao;

    @Autowired
    private SeminarDao seminarDao;

    @Autowired
    private KlassSeminarDao klassSeminarDao;

    public void deleteCourseById(BigInteger courseId) {
        courseDao.deleteCourse(courseId);
    }

    public void createCourse(Course course) throws Exception {
        courseDao.createCourse(course);
        List<Strategy> strategies = new ArrayList<>();
        if (course.getTeamAndStrategy() != null) {
            strategies.add(course.getTeamAndStrategy());
        }
        if (course.getTeamOrStrategy() != null) {
            strategies.add(course.getTeamOrStrategy());
        }
        if (course.getMemberLimitStrategy() != null) {
            strategies.add(course.getMemberLimitStrategy());
        }
        if (course.getConflictCourseStrategy() != null) {
            strategies.add(course.getConflictCourseStrategy());
        }
        if (course.getCourseMemberLimitStrategies() != null) {
            strategies.addAll(course.getCourseMemberLimitStrategies());
        }
        strategyDao.newStrategy(course.getCourseId(), strategies);
    }

    public Course getCourse(BigInteger courseId) throws Exception {
        Course course = courseDao.getCourse(courseId);
        if (course == null) {
            throw new Exception(noCourseError);
        }
        return course;
    }

    public List<Team> getTeamInCourse(BigInteger courseId) {
        Course mainCourse = courseDao.getTeamMainCourse(courseId);
        if (mainCourse != null) {
            BigInteger mainCourseId = mainCourse.getCourseId();
            return teamDao.getSimpleTeamInShareCourse(mainCourseId, courseId);
        }
        return teamDao.getSimpleTeamInCourse(courseId);
    }

    public void newKlass(BigInteger courseId, Klass klass) {
        klassDao.newKlass(courseId, klass);
        List<Seminar> seminars = seminarDao.getSeminarByCourseId(courseId);
        for (Seminar seminar : seminars) {
            klassSeminarDao.insertKlassSeminar(klass.getKlassId(), seminar.getSeminarId());
        }
    }

    public List<Klass> getKlassInCourse(BigInteger courseId) {
        return klassDao.getAllKlass(courseId);
    }

    public void deleteKlass(BigInteger klassId) {
        klassDao.deleteKlass(klassId);
    }

    public List<Object> getShareInCourse(BigInteger courseId) {
        List<Object> shares = new ArrayList<>();
        shares.addAll(shareTeamDao.getShareTeamInCourse(courseId));
        shares.addAll(shareSeminarDao.getShareSeminarInCourse(courseId));
        return shares;
    }

    public List<ShareTeam> getShareTeamInCourse(BigInteger courseId) {
        return shareTeamDao.getShareTeamInCourse(courseId);
    }

    public List<ShareSeminar> getShareSeminarInCourse(BigInteger courseId) {
        return shareSeminarDao.getShareSeminarInCourse(courseId);
    }

    public List<Klass> getKlassByStudent(BigInteger studentId) {
        return klassDao.getKlassByStudent(studentId);
    }

    public List<Course> getMainShareCourseByTeacher(BigInteger teacherId) {
        return klassDao.getMainShareCourseByTeacher(teacherId);
    }

    public List<Course> getSubShareCourseByTeacher(BigInteger teacherId) {
        return klassDao.getSubShareCourseByTeacher(teacherId);
    }

    public Integer deleteShareTeam(BigInteger shareTeamId) {
        return shareTeamDao.deleteShareTeam(shareTeamId);
    }

    public Integer deleteShareSeminar(BigInteger seminarShareId) {
        return shareSeminarDao.deleteShareSeminar(seminarShareId);
    }

    public ShareTeam newShareTeam(ShareTeam shareTeam) throws Exception {
        return shareTeamDao.newShareTeam(shareTeam);
    }

    public ShareSeminar newShareSeminar(ShareSeminar shareSeminar) {
        return shareSeminarDao.newShareSeminar(shareSeminar);
    }

    public List<ShareTeam> getShareTeamByTeacherId(BigInteger teacherId) {
        return shareTeamDao.getShareTeamByTeacherId(teacherId);
    }

    public List<ShareSeminar> getShareSeminarByTeacherId(BigInteger teacherId) {
        return shareSeminarDao.getShareSeminarByTeacherId(teacherId);
    }

    public List<TeamApplication> getTeamApplicationByTeacherId(BigInteger teacherId) {
        return teamApplicationDao.getTeamApplicationByTeacherId(teacherId);
    }

    public ShareTeam updateShareTeam(ShareTeam shareTeam) {
        ShareTeam newShareTeam = shareTeamDao.updateShareTeam(shareTeam);
        if (newShareTeam.getStatus()) {
            Course masterCourse = newShareTeam.getMasterCourse();
            Course receiveCourse = newShareTeam.getReceiveCourse();
            receiveCourse.setTeamMainCourse(masterCourse);

            courseDao.updateCourseTeamMainCourse(receiveCourse);
        }
        return newShareTeam;
    }

    public ShareSeminar updateShareSeminar(ShareSeminar shareSeminar) {
        ShareSeminar newShareSeminar = shareSeminarDao.updateShareSeminar(shareSeminar);
        Course masterCourse = newShareSeminar.getMasterCourse();
        Course receiveCourse = newShareSeminar.getReceiveCourse();
        receiveCourse.setTeamMainCourse(masterCourse);
        courseDao.updateCourseSeminarMainCourse(receiveCourse);
        List<Round> rounds = roundDao.getRoundByCourseId(masterCourse.getCourseId());
        List<Klass> klasses = klassDao.getAllKlass(receiveCourse.getCourseId());
        for (Round round : rounds) {
            for (Klass klass : klasses) {
                klassDao.addKlassRound(klass.getKlassId(), round.getRoundId());
            }
        }
        return newShareSeminar;
    }

    public TeamApplication updateTeamApplication(TeamApplication teamApplication) {
        return teamApplicationDao.updateTeamApplication(teamApplication);
    }

    public void uploadKlassFile(BigInteger klassId, List<Student> students) throws Exception {
        List<Student> newStudents = studentDao.newStudent(students);
        klassDao.addStudentInKlass(klassId, newStudents);
    }

    public Team getStudentTeamInKlass(BigInteger studentId, BigInteger klassId) {
        return teamDao.getStudentTeamInKlass(studentId, klassId);
    }

    public Strategy getCourseStrategy(BigInteger courseId) {
        Course teamMainCourse = courseDao.getTeamMainCourse(courseId);
        if (teamMainCourse != null) {
            courseId = teamMainCourse.getCourseId();
        }
        return strategyDao.getCourseStrategy(courseId);
    }

    public void newCourseStrategy(BigInteger courseId, List<Strategy> strategies) throws Exception {
        strategyDao.newStrategy(courseId, strategies);
    }

    public Team getStudentTeamInCourse(BigInteger studentId, BigInteger courseId) {
        Course mainCourse = courseDao.getTeamMainCourse(courseId);
        if (mainCourse != null) {
            courseId = mainCourse.getCourseId();
        }
        return teamDao.getStudentTeamInCourse(studentId, courseId);
    }

    public Team getStudentTeamInKlassSeminar(BigInteger studentId, BigInteger klassSeminarId) {
        return teamDao.getStudentTeamInKlassSeminar(studentId, klassSeminarId);
    }

    public List<Course> getAllCourse() {
        return teamDao.getAllCourse();
    }

    public List<Course> getCourseByTeacher(BigInteger teacherId) {
        return courseDao.getAllCoursesByTeacherId(teacherId);
    }
}
