package com.xmu.cms.dao.Impl;

import com.xmu.cms.dao.KlassDao;
import com.xmu.cms.entity.Course;
import com.xmu.cms.entity.Klass;
import com.xmu.cms.entity.Student;
import com.xmu.cms.mapper.CourseMapper;
import com.xmu.cms.mapper.KlassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/16.
 * @version 1.0
 */
@Component
public class KlassDaoImpl implements KlassDao {
    @Autowired
    private KlassMapper klassMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Integer newKlass(BigInteger courseId, Klass klass) {
        Course course = courseMapper.getCourseById(courseId);
        klass.setCourse(course);
        return klassMapper.insertKlass(klass);
    }

    @Override
    public List<Klass> getAllKlass(BigInteger courseId) {
        //return klassMapper.getAllKlass(courseId);
        //todo
        return null;
    }

    @Override
    public Integer addStudentInKlass(BigInteger klassId, List<Student> students) {
        Klass klass = klassMapper.getKlassByKlassId(klassId);
        Integer count = 0;
        for (Student student : students) {
            count += klassMapper.addStudent(klass.getCourse().getCourseId(), klassId, student);
        }
        return count;
    }

    @Override
    public List<Klass> getKlassByStudent(BigInteger studentId) {
        return klassMapper.getKlassByStudent(studentId);
    }

    @Override
    public List<Course> getMainShareCourseByTeacher(BigInteger teacherId) {
        return courseMapper.getMainShareCourseByTeacher(teacherId);
    }

    @Override
    public List<Course> getSubShareCourseByTeacher(BigInteger teacherId) {
        return courseMapper.getSubShareCourseByTeacher(teacherId);
    }
}
