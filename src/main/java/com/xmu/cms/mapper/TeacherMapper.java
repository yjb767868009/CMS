package com.xmu.cms.mapper;

import com.xmu.cms.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author JuboYu on 2018/12/11.
 * @version 3.0
 */
@Mapper
public interface TeacherMapper {
    /**
     * get all teachers in system
     *
     * @return List<Teacher> the list of teacher
     */
    List<Teacher> getAllTeachers();

    /**
     * create a new teacher
     *
     * @param teacher new teacher
     * @return the num of new teacher
     */
    Integer createTeacher(@Param("teacher") Teacher teacher);

    /**
     * delete teacher by his id
     *
     * @param teacherId teacher's id
     * @return the number of delete teacher
     */
    Integer deleteTeacherByTeacherId(@Param("teacherId") Integer teacherId);

    /**
     * get teacher's password by account
     *
     * @param account log in account
     * @return teacher
     */
    Teacher getTeacherByAccount(@Param("account") String account);

    /**
     * search teacher by name
     *
     * @param name teacher's name
     * @return List<teacher>
     */
    List<Teacher> getTeacherByName(@Param("name") String name);

    /**
     * get teacher by his id
     *
     * @param teacherId teacher's id
     * @return teacher
     */
    Teacher getTeacherById(@Param("teacherId") Integer teacherId);

    /**
     * use new teacher update old teacher
     *
     * @param teacher updated teacher
     * @return the number of update teacher
     */
    Integer updateTeacher(@Param("teacher") Teacher teacher);

    /**
     * update teacher's info
     *
     * @param teacherId need update teacher's id
     * @param account   teacher new account
     * @param name      teacher new name
     * @param email     teacher new email
     * @return the num of update teacher
     */
    Integer updateTeacherInfo(@Param("teacherId") Integer teacherId,
                              @Param("account") String account,
                              @Param("name") String name,
                              @Param("email") String email);

    /**
     * update teacher's password
     *
     * @param teacherId need update teacher id
     * @param password  teacher new password
     * @return the num of update teacher
     */
    Integer updateTeacherPassword(@Param("teacherId") Integer teacherId,
                                  @Param("password") String password);
}
