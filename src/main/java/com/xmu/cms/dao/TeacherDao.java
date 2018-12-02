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
     * @param name     teacher's name
     * @param account  teacher's account
     * @param password teacher's password
     * @param email    teacher's email
     * @return String message
     */
    Integer newTeacher(@Param("name") String name,
                       @Param("account") String account,
                       @Param("password") String password,
                       @Param("email") String email,
                       @Param("phone") String phone,
                       @Param("sex") String sex,
                       @Param("messageInterval") Integer messageInterval,
                       @Param("activation") Boolean activation);

    /**
     * modify teacher by his id
     *
     * @param id      teacher's id
     * @param name    teacher's new name
     * @param account teacher's new account
     * @param email   teacher's new email
     * @return String message
     */
    Integer modifyTeacherById(@Param("id") Integer id,
                              @Param("name") String name,
                              @Param("account") String account,
                              @Param("email") String email);

    /**
     * modify teacher's password by his id
     *
     * @param id       teacher's id
     * @param password teacher's new password
     * @return String message
     */
    Integer modifyTeacherPasswordById(@Param("id") Integer id,
                                      @Param("password") String password);

    /**
     * delete teacher by his id
     *
     * @param id teacher's id
     * @return String message
     */
    Integer deleteTeacherById(Integer id);

    /**
     * get teacher's password by account
     *
     * @param account log in account
     * @return account's password
     */
    Teacher getTeacherByAccount(@Param("account") String account);

    /**
     * search teacher by name
     *
     * @param name teacher's name
     * @return List<teacher>
     */
    List<Teacher> getTeacherByName(@Param("name") String name);
}
