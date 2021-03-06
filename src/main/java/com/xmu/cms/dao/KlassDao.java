package com.xmu.cms.dao;

import com.xmu.cms.entity.Course;
import com.xmu.cms.entity.Klass;
import com.xmu.cms.entity.Student;
import com.xmu.cms.mapper.CourseMapper;
import com.xmu.cms.mapper.KlassMapper;
import com.xmu.cms.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/16.
 * @version 1.0
 */
@Component
public class KlassDao {
    @Autowired
    private KlassMapper klassMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private StudentMapper studentMapper;

    public void newKlass(BigInteger courseId, Klass klass) {
        Course course = courseMapper.getCourseById(courseId);
        klass.setCourse(course);
        klassMapper.insertKlass(klass);
    }

    public List<Klass> getAllKlass(BigInteger courseId) {
        return klassMapper.getKlassesInCourse(courseId);
    }

    public void addStudentInKlass(BigInteger klassId, List<Student> students) {
        Klass klass = klassMapper.getKlassByKlassId(klassId);
        for (Student student : students) {
            Student findStudent = studentMapper.getKlassStudent(klassId, student.getStudentId());
            if (findStudent == null) {
                klassMapper.addStudent(klass.getCourse().getCourseId(), klassId, student);
            }
        }
    }

    public List<Klass> getKlassByStudent(BigInteger studentId) {
        return klassMapper.getKlassByStudent(studentId);
    }

    public List<Course> getMainShareCourseByTeacher(BigInteger teacherId) {
        return courseMapper.getMainShareCourseByTeacher(teacherId);
    }

    public List<Course> getSubShareCourseByTeacher(BigInteger teacherId) {
        return courseMapper.getSubShareCourseByTeacher(teacherId);
    }

    public Klass getKlass(BigInteger klassId) {
        return klassMapper.getKlassByKlassId(klassId);
    }

    public void addKlassRound(BigInteger klassId, BigInteger roundId) {
        klassMapper.addKlassRound(klassId, roundId);
    }

    public void deleteKlass(BigInteger klassId) {
        klassMapper.deleteKlassByKlassId(klassId);
    }

}
