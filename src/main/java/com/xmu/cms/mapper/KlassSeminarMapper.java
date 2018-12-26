package com.xmu.cms.mapper;

import com.xmu.cms.entity.KlassSeminar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;


/**
 * @author JuboYu on 2018/12/20.
 * @version 1.0
 */
@Mapper
public interface KlassSeminarMapper {
    /**
     * 通过班级和讨论课获取班级对应的讨论课
     *
     * @param klassId   班级id
     * @param seminarId 讨论课id
     * @return 班级讨论课
     */
    KlassSeminar getKlassSeminarByKlassAndSeminar(@Param("klassId") BigInteger klassId,
                                                  @Param("seminarId") BigInteger seminarId);

    /**
     * 删除班级讨论课
     *
     * @param klassSeminarId 班级讨论课id
     * @return 删除信息
     */
    Integer deleteKlassSeminar(@Param("klassSeminarId") BigInteger klassSeminarId);


    /**
     * 删除讨论课成绩
     *
     * @param klassSeminarId 班级讨论课id
     * @return 删除信息
     */
    Integer deleteKlassSeminarScore(@Param("klassSeminarId") BigInteger klassSeminarId);

    /**
     * 获取讨论课底下的所有班级讨论课
     *
     * @param seminarId 讨论课id
     * @return 班级讨论课列表
     */
    List<KlassSeminar> getKlassSeminarBySeminarId(@Param("seminarId") BigInteger seminarId);

    /**
     * 修改班级讨论课
     *
     * @param klassSeminar 班级讨论课
     */
    Integer updateKlassSeminar(@Param("klassSeminar") KlassSeminar klassSeminar);

    /**
     * 根据学生和讨论课获取班级讨论课
     *
     * @param studentId 队伍id
     * @param seminarId 讨论课id
     * @return 班级讨论课
     */
    KlassSeminar getKlassSeminarByStudentAndSeminar(@Param("studentId") BigInteger studentId,
                                                    @Param("seminarId") BigInteger seminarId);

    KlassSeminar getRunningKlassSeminarByTeacherId(@Param("teacherId") BigInteger teacherId);

    /**
     * 停止班级讨论课
     *
     * @param klassSeminarId 班级讨论课id
     */
    void stopKlassSeminar(@Param("klassSeminarId") BigInteger klassSeminarId);

    /**
     * 开始班级讨论课
     *
     * @param seminarId 讨论课id
     * @param klassId   班级id
     */
    void startKlassSeminar(@Param("seminarId") BigInteger seminarId,
                           @Param("klassId") BigInteger klassId);

    /**
     * 开始班级讨论课，把班级讨论课状态置为开始
     *
     * @param klassSeminarId 班级讨论课id
     */
    void startKlassSeminar(BigInteger klassSeminarId);// TODO: 2018/12/25

    /**
     * 新建班级讨论课，ddl为空，状态为0
     *
     * @param klassId   班级id
     * @param seminarId 讨论课id
     */
    void insertKlassSeminar(BigInteger klassId, BigInteger seminarId);// TODO: 2018/12/26
}
