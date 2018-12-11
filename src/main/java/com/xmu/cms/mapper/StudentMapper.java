package com.xmu.cms.mapper;

import com.xmu.cms.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author BiqunZhou on 2018/12/5.
 * @version 2.0
 */


@Mapper
public interface StudentMapper {
    /**
     * get all students
     *
     * @return List<Student>
     */
    List<Student> getAllStudents();

    /**
     * add new student
     *
     * @param student new student
     * @return if new student success
     */
    Integer insertStudent(Student student);

    /**
     * update student
     *
     * @param student new student
     * @return if update student success
     */
    Integer updateStudent(@Param("student") Student student);

    /**
     * delete student by his id
     *
     * @param studentId student's id
     * @return String message
     */
    Integer deleteStudent(@Param("studentId") Integer studentId);

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

    /**
     * get student by his id
     *
     * @param studentId student's id
     * @return student
     */
    Student getStudentById(@Param("studentId") Integer studentId);
}
