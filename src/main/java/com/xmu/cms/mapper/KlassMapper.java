package com.xmu.cms.mapper;

import com.xmu.cms.entity.Course;
import com.xmu.cms.entity.Klass;
import com.xmu.cms.entity.Seminar;
import com.xmu.cms.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@Mapper
public interface KlassMapper {
    /**
     * 新建班级
     *
     * @param klass 班级对象
     * @return 新建成功信息
     */
    Integer insertKlass(Klass klass);

    /**
     * 删除班级
     *
     * @param klassId 班级id
     * @return 删除成功信息
     */
    Integer deleteKlassByKlassId(@Param("klassId") BigInteger klassId);

    /**
     * 通过班级id获取班级
     *
     * @param klassId 班级id
     * @return 班级对象
     */
    Klass getKlassByKlassId(@Param("kassId") BigInteger klassId);

    /**
     * 在班级学生关系表中加入新学生的信息
     *
     * @param courseId 课程id
     * @param klassId  班级id
     * @param student  待加入的学生
     * @return 插入学生成功信息
     */
    Integer addStudent(@Param("courseId") BigInteger courseId,
                       @Param("kassId") BigInteger klassId,
                       @Param("student") Student student);

    /**
     * 获取班级里的所有课程
     *
     * @param courseId 课程id
     * @return 班级列表
     */
    List<Klass> getKlassesInCourse(@Param("courseId") BigInteger courseId);

    /**
     * 删除班级轮次信息
     *
     * @param klassId 班级信息
     * @param roundId 轮次信息
     * @return 删除信息
     */
    Integer deleteKlassRound(@Param("kassId") BigInteger klassId,
                             @Param("roundId") BigInteger roundId);

    /**
     * 删除班级与学生的关系
     *
     * @param klassId 班级id
     * @return 删除信息
     */
    Integer deleteKlassStudent(@Param("kassId") BigInteger klassId);


    /**
     * 删除班级学生关系中队伍信息
     *
     * @param teamId 队伍id
     */
    Integer deleteTeamStudent(BigInteger teamId);

    /**
     * 队伍增加学生
     *
     * @param klassId   班级id
     * @param teamId    队伍id
     * @param studentId 学生id
     * @return 修改信息
     */
    Integer addMembers(BigInteger klassId, BigInteger teamId, BigInteger studentId);

    /**
     * 删除单个学生和队伍的关系
     *
     * @param teamId  队伍id
     * @param student 学生id
     * @return 修改信息
     */
    Integer removeTeamStudent(BigInteger teamId, Student student);

    /**
     * 增加班级与轮次的关系
     *
     * @param klassId      班级id
     * @param roundId      轮次id
     * @param enrollNumber 最大报名数
     * @return 新建信息
     */
    Integer insertKlassRound(Integer klassId, BigInteger roundId, Integer enrollNumber);

    /**
     * 获取学生的所有班级
     *
     * @param studentId 学生id
     * @return 班级列表
     */
    List<Klass> getKlassByStudent(@Param("studentId") BigInteger studentId);

    /**
     * 删除课程中所有学生的队伍
     *
     * @param course 课程
     */
    void deleteCourseStudentTeam(Course course);

    /**
     * 新建班级和轮次的关系，enroll默认为1
     *
     * @param klassId 班级id
     * @param roundId 轮次id
     */
    void addKlassRound(BigInteger klassId, BigInteger roundId);
}
