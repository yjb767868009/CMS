package com.xmu.cms.mapper;

import com.xmu.cms.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
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
     * 新建老师
     *
     * @param teacher 老师
     * @return 新建老师的id
     * @throws Exception 新建错误报错
     */
    Integer insertTeacher(@Param("teacher") Teacher teacher) throws Exception;

    /**
     * delete teacher by his id
     *
     * @param teacherId teacher's id
     * @return the number of delete teacher
     */
    Integer deleteTeacher(@Param("teacherId") BigInteger teacherId);

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
    Teacher getTeacherById(@Param("teacherId") BigInteger teacherId);

    /**
     * use new teacher update old teacher
     *
     * @param teacher updated teacher
     * @return the number of update teacher
     */
    Integer updateTeacher(@Param("teacher") Teacher teacher);
}
