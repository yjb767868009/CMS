package com.xmu.cms.dao;

import com.xmu.cms.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author JuboYu on 2018/11/24.
 * @version 1.0
 */
@Mapper
public interface TeacherDao {
    /**
     * get all teachers in system
     *
     * @return List<Teacher> the list of teacher
     */
    List<Teacher> getAllTeachers();

    /**
     * create a new teacher
     *
     * @param name teacher's name
     * @param account teacher's account
     * @param password teacher's password
     * @param email teacher's email
     * @return String message
     */
    String newTeacher(@Param("name") String name,
                      @Param("account") String account,
                      @Param("password") String password,
                      @Param("email") String email);

    /**
     * modify teacher by his id
     *
     * @param teacherId teacher's id
     * @param name teacher's new name
     * @param account teacher's new account
     * @param email teacher's new email
     * @return String message
     */
    String modifyTeacherById(@Param("teacherId") Long teacherId,
                             @Param("name") String name,
                             @Param("account") String account,
                             @Param("email") String email);

    /**
     * modify teacher's password by his id
     *
     * @param teacherId teacher's id
     * @param password teacher's new password
     * @return String message
     */
    String modifyTeacherPasswordById(@Param("teacherId") Long teacherId,
                                     @Param("password") String password);

    /**
     * delete teacher by his id
     *
     * @param teacherId teacher's id
     * @return String message
     */
    String deleteTeacherById(Long teacherId);
}
