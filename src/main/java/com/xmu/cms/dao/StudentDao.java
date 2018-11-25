package com.xmu.cms.dao;

import com.xmu.cms.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     * @param name student's new name
     * @param account student's new account
     * @param email student's new email
     * @return String message
     */
    String modifyStudentById(@Param("studentId")Long studentId,
                             @Param("name") String name,
                             @Param("account") String account,
                             @Param("email") String email);

    /**
     * modify student's password by his id
     *
     * @param studentId student's id
     * @param password student's new password
     * @return String message
     */
    String modifyStudentPasswordById(@Param("studentId") Long studentId,
                                     @Param("password") String password);

    /**
     * delete student by his id
     *
     * @param studentId student's id
     * @return String message
     */
    String deleteStudentById(Long studentId);
}
