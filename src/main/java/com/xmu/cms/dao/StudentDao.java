package com.xmu.cms.dao;

import com.xmu.cms.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author JuboYu on 2018/11/23.
 * @version 1.0
 */


@Mapper
public interface StudentDao {
    /**
     * get all students
     *
     * @return List<Student>
     */
    List<Student> getAllStudents();

    /**
     * create a new student
     *
     * @param student student entity
     * @return String message
     */
    String newStudent(Student student);

    /**
     * modify student by his id
     *
     * @param studentId student's id
     * @return String message
     */
    String modifyStudentById(Long studentId);

    /**
     * delete student by his id
     *
     * @param studentId student's id
     * @return String message
     */
    String deleteStudent(Long studentId);
}
