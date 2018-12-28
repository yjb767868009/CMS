package com.xmu.cms.mapper;

import com.xmu.cms.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
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
    Integer insertStudent(@Param("student") Student student);

    /**
     * update student
     *
     * @param student new student
     * @return if update student success
     */
    Integer updateStudent(@Param("student") Student student);

    /**
     * 更新学生密码
     *
     * @param student 学生对象
     * @return 更新成功信息
     */
    Integer updateStudentPassword(@Param("student") Student student);

    /**
     * 更新学生信息
     *
     * @param student 学生对象
     * @return 更新成功信息
     */
    Integer updateStudentInfo(@Param("student") Student student);

    /**
     * delete student by his id
     *
     * @param studentId student's id
     * @return String message
     */
    Integer deleteStudent(@Param("studentId") BigInteger studentId);

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
    Student getStudentById(@Param("studentId") BigInteger studentId);

    /**
     * 获取课程中未组队学生
     *
     * @param courseId 课程id
     * @return 学生列表
     */
    List<Student> getNoTeamStudentInCourse(@Param("courseId") BigInteger courseId);

    /**
     * 获取队伍中的成员信息
     *
     * @param teamId 队伍id
     * @return 成员列表
     */
    List<Student> getMembersInTeam(@Param("teamId") BigInteger teamId);

    /**
     * 获取课程下的所有学生
     *
     * @param courseId 课程id
     * @return 学生列表
     */
    List<Student> getStudentInCourse(BigInteger courseId);

    /**
     * 激活学生
     *
     * @param student 学生
     */
    void activeStudent(@Param("student") Student student);
}
