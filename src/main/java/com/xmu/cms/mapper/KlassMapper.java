package com.xmu.cms.mapper;

import com.xmu.cms.entity.Klass;
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
    Integer insertKlass(@Param("klass") Klass klass);

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
    Integer deleteTeamStudent(@Param("teamId") BigInteger teamId);

    /**
     * 队伍增加学生
     *
     * @param teamId    队伍id
     * @param studentId 学生id
     * @return 修改信息
     */
    Integer addMembers(@Param("teamId") BigInteger teamId,
                       @Param("studentId") BigInteger studentId);

    /**
     * 删除单个学生和队伍的关系
     *
     * @param teamId  队伍id
     * @param student 学生id
     * @return 修改信息
     */
    Integer removeTeamStudent(@Param("teamId") BigInteger teamId,
                              @Param("student") Student student);

    /**
     * 增加班级与轮次的关系
     *
     * @param klassId      班级id
     * @param roundId      轮次id
     * @param enrollNumber 最大报名数
     * @return 新建信息
     */
    Integer insertKlassRound(@Param("klassId") BigInteger klassId,
                             @Param("roundId") BigInteger roundId,
                             @Param("enrollNumber") Integer enrollNumber);

    /**
     * 获取学生的所有班级
     *
     * @param studentId 学生id
     * @return 班级列表
     */
    List<Klass> getKlassByStudent(@Param("studentId") BigInteger studentId);

    /**
     * 新建班级和轮次的关系，enroll默认为1
     *
     * @param klassId 班级id
     * @param roundId 轮次id
     */
    void addKlassRound(@Param("klassId") BigInteger klassId,
                       @Param("roundId") BigInteger roundId);

    /**
     * 删除班级队伍信息的表中队伍的信息
     *
     * @param teamId 队伍id
     */
    void deleteKlassTeam(@Param("teamId") BigInteger teamId);

    /**
     * 添加班级队伍
     *
     * @param klassId 班级id
     * @param teamId 队伍id
     */
    void addTeam(@Param("klassId") BigInteger klassId,
                 @Param("teamId") BigInteger teamId);
}
