package com.xmu.cms.controller;

import com.xmu.cms.entity.Course;
import com.xmu.cms.entity.Seminar;
import com.xmu.cms.entity.Student;
import com.xmu.cms.service.AttendanceService;
import com.xmu.cms.service.KlassService;
import com.xmu.cms.service.CourseService;
import com.xmu.cms.service.StudentService;
import com.xmu.cms.support.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private KlassService klassService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private AttendanceService attendanceService;

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @Secured("ROLE_STUDENT")
    @PostMapping(value = "/active")
    public Map<String, String> activateStudent(@RequestParam(value = "userId") Integer userId,
                                               @RequestParam(value = "userType") String userType,
                                               @RequestParam(value = "student") Student student) {
        return studentService.activateStudent(userId, student);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping(value = "/{studentId}/information")
    public Map<String, String> modifyStudentInfo(@PathVariable("studentId") Integer studentId,
                                                 @RequestBody Student student) {
        return studentService.modifyStudentInfo(studentId, student);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping(value = "/{studentId}/password")
    public Map<String, String> modifyStudentPasswordById(@PathVariable("studentId") Integer studentId,
                                                         @RequestBody Student student) {
        return studentService.modifyStudentPassword(studentId, student);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping(value = "/{studentId}")
    public Map<String, String> deleteStudent(@PathVariable("studentId") Integer studentId) {
        return studentService.deleteStudent(studentId);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/searchByName")
    public List<Student> getStudentByName(@RequestParam("name") String name) {
        return studentService.getStudentByName(name);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/searchByAccount")
    public Student getStudentByAccount(@RequestParam("account") String account) {
        return studentService.getStudentByAccount(account);
    }

    @PostMapping(value = "/klassSeminar/{klassSeminarId}/attendance")
    public Map<String, String> attendance(@PathVariable("klassSeminarId") Integer klassSeminarId,
                                          @RequestParam("teamOrder") Integer teamOrder) {
        return attendanceService.newAttendance(klassSeminarId, teamOrder);
    }

    @PostMapping(value = "/klassSeminar/{klassSeminarId}/score")
    public Map<String, String> getScoreInKlassSeminar(@PathVariable("klassSeminarId") Integer klassSeminarId,
                                                      @RequestParam("teamOrder") Integer teamOrder) {
        return attendanceService.newAttendance(klassSeminarId, teamOrder);
    }

}
