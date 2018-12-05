package com.xmu.cms.dao;

import com.xmu.cms.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author BiqunZhou on 2018/12/5.
 * @version 2.0
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
     * @param name     student's name
     * @param account  student's account
     * @param password student's password
     * @param email    student's email
     * @return String message
     */
    Integer newStudent(@Param("name") String name,
                       @Param("account") String account,
                       @Param("password") String password,
                       @Param("email") String email,
                       @Param("phone") String phone,
                       @Param("messageInterval") Integer messageInterval,
                       @Param("activation") Boolean activation);

    /**
     * modify student by his id
     *
     * @param studentId student's id
     * @param name      student's new name
     * @param account   student's new account
     * @param email     student's new email
     * @return String message
     */
    Integer modifyStudentByStudentId(@Param("studentId") Integer studentId,
                                     @Param("name") String name,
                                     @Param("account") String account,
                                     @Param("email") String email);

    /**
     * modify student's password by his id
     *
     * @param studentId student's id
     * @param password  student's new password
     * @return String message
     */
    Integer modifyStudentPasswordByStudentId(@Param("studentId") Integer studentId,
                                             @Param("password") String password);

    /**
     * delete student by his id
     *
     * @param studentId student's id
     * @return String message
     */
    Integer deleteStudentByStudentId(@Param("studentId") Integer studentId);

    /**
     * get student's password by account
     *
     * @param account log in account
     * @return account's password
     */
    Student getStudentByAccount(@Param("account") String account);

    /**
     * search teacher by name
     *
     * @param name student's name
     * @return List<Student>
     */
    List<Student> getStudentByName(@Param("name") String name);

    String getEmailByAccount(@Param("email") String email);

    Student getStudentById(@Param("studentId") Integer studentId);
}
