package com.xmu.cms.dao;

import com.xmu.cms.entity.Course;
import com.xmu.cms.entity.Klass;
import com.xmu.cms.entity.Student;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/16.
 * @version 1.0
 */
public interface KlassDao {
    Integer newKlass(BigInteger courseId, Klass klass);

    List<Klass> getAllKlass(BigInteger courseId);

    Integer addStudentInKlass(BigInteger klassId, List<Student> students);

    List<Klass> getKlassByStudent(BigInteger studentId);

    List<Course> getMainShareCourseByTeacher(BigInteger teacherId);

    List<Course> getSubShareCourseByTeacher(BigInteger teacherId);

    Klass getKlass(BigInteger klassId);

    void deleteCourseStudentTeam(Course receiveCourse);

    void addKlassRound(BigInteger klassId, BigInteger roundId);

    void deleteKlass(BigInteger klassId);
}
