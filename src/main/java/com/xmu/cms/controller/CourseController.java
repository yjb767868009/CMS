package com.xmu.cms.controller;

import com.xmu.cms.aspect.annoatation.CheckCoursePermission;
import com.xmu.cms.entity.*;
import com.xmu.cms.entity.strategy.Strategy;
import com.xmu.cms.service.CourseService;
import com.xmu.cms.service.MailService;
import com.xmu.cms.service.SeminarService;
import com.xmu.cms.service.UserService;
import com.xmu.cms.support.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/12/16.
 * @version 1.0
 */
@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @Autowired
    private SeminarService seminarService;

    @Autowired
    private MailService mailService;

    @Secured("ROLE_TEACHER")
    @PostMapping(value = "/course")
    public Map<String, String> createCourse(@RequestBody Course course) throws Exception {
        Map<String, String> message = new HashMap<String, String>(1);
        try {
            courseService.createCourse(course);
            message.put("message", "Success");
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }
        return message;
    }


    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/course")
    public Map<String, Object> getCourses(UserInfo info) {
        Map<String, Object> message = new HashMap<String, Object>(2);
        Map<BigInteger, String> coursePlus = new HashMap<BigInteger, String>(16);
        List<Course> courses = new ArrayList<Course>();
        switch (info.getUserType()) {
            case "teacher":
                List<Course> mainShareCourses = courseService.getMainShareCourseByTeacher(info.getUserId());
                for (Course course : mainShareCourses) {
                    courses.add(course);
                    coursePlus.put(course.getCourseId(), "主");
                }
                List<Course> subShareCourses = courseService.getSubShareCourseByTeacher(info.getUserId());
                for (Course course : subShareCourses) {
                    courses.add(course);
                    coursePlus.put(course.getCourseId(), "从");
                }
                break;
            case "student":
                List<Klass> klasses = courseService.getKlassByStudent(info.getUserId());
                for (Klass klass : klasses) {
                    courses.add(klass.getCourse());
                    coursePlus.put(klass.getCourse().getCourseId(), klass.getName());
                }
                break;
            default:
                return null;
        }
        message.put("courses", courses);
        message.put("coursePlus", coursePlus);
        return message;
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/course/{courseId}/round")
    public List<Round> getRoundInCourse(UserInfo info,
                                        @PathVariable("courseId") BigInteger courseId) {
        return seminarService.getRoundInCourse(info, courseId);
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/course/{courseId}")
    public Object getCourse(@PathVariable("courseId") BigInteger courseId) {
        try {
            return courseService.getCourse(courseId);
        } catch (Exception e) {
            return "无此课";
        }
    }

    @Secured("ROLE_TEACHER")
    @CheckCoursePermission
    @DeleteMapping(value = "/course/{courseId}")
    public Map<String, String> deleteCourse(@PathVariable("courseId") BigInteger courseId) {
        Map<String, String> message = new HashMap<String, String>(1);
        try {
            courseService.deleteCourseById(courseId);
            message.put("message", "Success");
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }
        return message;
    }

    @Secured("ROLE_TEACHER")
    @PostMapping(value = "/course/{courseId}/round")
    public Map<String, String> newRound(@PathVariable("courseId") BigInteger courseId,
                                        @RequestBody Round round) {
        round.setCourse(new Course(courseId));
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = seminarService.newRound(round);
        if (count > 0) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/course/{courseId}/team")
    public Map<String, Object> getTeamInCourse(UserInfo info, @PathVariable("courseId") BigInteger courseId) {
        Map<String, Object> message = new HashMap<>(2);
        String student = "student";
        if (info.getUserType().equals(student)) {
            message.put("myTeam", courseService.getStudentTeamInCourse(info.getUserId(), courseId));
        }
        message.put("teams", courseService.getTeamInCourse(courseId));
        return message;
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/course/{courseId}/noteam")
    public List<Student> getNoTeamStudent(@PathVariable("courseId") BigInteger courseId) {
        return userService.getNoTeamStudent(courseId);
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/course/{courseId}/class")
    public List<Klass> getKlassInCourse(@PathVariable("courseId") BigInteger courseId) {
        return courseService.getKlassInCourse(courseId);
    }

    @Secured("ROLE_TEACHER")
    @CheckCoursePermission
    @PostMapping(value = "/course/{courseId}/class")
    public Map<String, String> createClass(@PathVariable("courseId") BigInteger courseId,
                                           @RequestBody Klass klass) {
        Map<String, String> message = new HashMap<String, String>(1);
        try {
            courseService.newKlass(courseId, klass);
            message.put("message", "Success");
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }
        return message;
    }

    @Secured("ROLE_TEACHER")
    @CheckCoursePermission
    @GetMapping(value = "/course/{courseId}/share")
    public List<Object> listAllShareByCourseId(@PathVariable("courseId") BigInteger courseId) {
        return courseService.getShareInCourse(courseId);
    }

    @Secured("ROLE_TEACHER")
    @CheckCoursePermission
    @GetMapping(value = "/course/{courseId}/shareteam")
    public List<ShareTeam> getShareTeamByCourseId(@PathVariable("courseId") BigInteger courseId) {
        return courseService.getShareTeamInCourse(courseId);
    }

    @Secured("ROLE_TEACHER")
    @GetMapping(value = "/course/{courseId}/shareseminar")
    public List<ShareSeminar> getShareSeminarByCourseId(@PathVariable("courseId") BigInteger courseId) {
        return courseService.getShareSeminarInCourse(courseId);

    }

    @Secured("ROLE_TEACHER")
    @CheckCoursePermission
    @DeleteMapping(value = "shareteam/{shareTeamId}")
    public Map<String, String> deleteShareTeam(@PathVariable("shareTeamId") BigInteger shareTeamId) {
        Map<String, String> message = new HashMap<String, String>(1);
        try {
            Integer count = courseService.deleteShareTeam(shareTeamId);
            if (count > 0) {
                message.put("message", "Success");
            } else {
                message.put("message", "Error");
            }
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }
        return message;
    }

    @Secured("ROLE_TEACHER")
    @CheckCoursePermission
    @DeleteMapping(value = "shareseminar/{shareSeminarId}")
    public Map<String, String> deleteSeminarShare(@PathVariable("shareSeminarId") BigInteger shareSeminarId) {
        Map<String, String> message = new HashMap<String, String>(1);
        try {
            Integer count = courseService.deleteShareSeminar(shareSeminarId);
            if (count > 0) {
                message.put("message", "Success");
            } else {
                message.put("message", "Error");
            }
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }
        return message;
    }

    @Secured("ROLE_TEACHER")
    @GetMapping(value = "/course/{courseId}/round/{roundId}/teamscore")
    public List<Map<String, Object>> getScoreInCourse(@PathVariable("courseId") BigInteger courseId,
                                                      @PathVariable("roundId") BigInteger roundId) {
        return seminarService.getRoundScoreInCourse(courseId, roundId);
    }

    @Secured("ROLE_TEACHER")
    @GetMapping(value = "/course/{courseId}/round/{roundId}/team/{teamId}/score")
    public Map<String, Object> getTeamScoreInCourse(@PathVariable("courseId") BigInteger courseId,
                                                    @PathVariable("roundId") BigInteger roundId,
                                                    @PathVariable("teamId") BigInteger teamId) {
        return seminarService.getTeamRoundScoreAndSeminarScore(teamId, courseId, roundId);
    }


    @Secured("ROLE_STUDENT")
    @GetMapping(value = "/course/{courseId}/round/{roundId}/score")
    public Map<String, Object> getStudentScoreInCourse(UserInfo info,
                                                       @PathVariable("courseId") BigInteger courseId,
                                                       @PathVariable("roundId") BigInteger roundId) {
        return seminarService.getStudentRoundScoreAndSeminarScore(info.getUserId(), courseId, roundId);
    }


    @Secured("ROLE_TEACHER")
    @CheckCoursePermission
    @PostMapping(value = "/course/{courseId}/teamsharerequest")

    public Map<String, String> sendShareTeam(@PathVariable("courseId") BigInteger courseId,
                                             @RequestBody ShareTeam shareTeam) {
        shareTeam.setMasterCourse(new Course(courseId));
        Map<String, String> message = new HashMap<String, String>(1);
        try {
            ShareTeam newShareTeam = courseService.newShareTeam(shareTeam);
            mailService.sendShareTeam(newShareTeam);
            message.put("message", "Success");
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }
        return message;
    }

    @Secured("ROLE_TEACHER")
    @CheckCoursePermission
    @PostMapping(value = "/course/{courseId}/seminarsharerequest")
    public Map<String, String> sendTeamShareRequest(@PathVariable("courseId") BigInteger courseId,
                                                    @RequestBody ShareSeminar shareSeminar) {
        shareSeminar.setMasterCourse(new Course(courseId));
        Map<String, String> message = new HashMap<String, String>(1);
        try {
            ShareSeminar newShareSeminar = courseService.newShareSeminar(shareSeminar);
            mailService.sendShareSeminar(newShareSeminar);
            message.put("message", "Success");
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }
        return message;
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/course/{courseId}/strategy")
    public List<Strategy> getCourseStrategy(@PathVariable("courseId") BigInteger courseId) {
        List<Strategy> strategies = new ArrayList<>();
        return courseService.getCourseStrategy(courseId).getStrategy(strategies);
    }

    @Secured("ROLE_TEACHER")
    @PostMapping(value = "/course/{courseId}/strategy")
    public Map<String, String> newCourseStrategy(@PathVariable("courseId") BigInteger courseId,
                                                 @RequestBody List<Strategy> strategies) {
        Map<String, String> message = new HashMap<>(1);
        try {
            courseService.newCourseStrategy(strategies);
            message.put("message", "Success");
        } catch (Exception e) {
            message.put("message", "Error");
        }
        return message;
    }
}
