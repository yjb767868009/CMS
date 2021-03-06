package com.xmu.cms.mapper;

import com.xmu.cms.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/20.
 * @version 1.0
 */
@Mapper
public interface QuestionMapper {
    /**
     * 获取班级讨论课的所有提问
     *
     * @param klassSeminarId 班级讨论课id
     * @return 提问列表
     */
    List<Question> getQuestionInKlassSeminar(@Param("klassSeminarId") BigInteger klassSeminarId);

    /**
     * 获取所有未选择的提问
     *
     * @param klassSeminarId 班级讨论课id
     * @return 提问列表
     */
    List<Question> getNoSelectedQuestionInKlassSeminar(@Param("klassSeminarId") BigInteger klassSeminarId);

    /**
     * 给提问打分
     *
     * @param question 提问信息
     * @return 修改信息
     */
    Integer scoreQuestion(@Param("question") Question question);

    /**
     * 发出提问
     *
     * @param question 新的提问
     * @return 新建提问的id
     */
    Integer insertQuestion(@Param("question") Question question);

    /**
     * 将提问置为选取状态
     *
     * @param question 提问
     */
    void selectQuestion(@Param("question") Question question);

    /**
     * 根据班级讨论课和学生获取提问
     *
     * @param klassSeminarId 班级讨论id
     * @param studentId      学生id
     * @return 提问
     */
    Question getQuestionByKlassSeminarAndStudent(@Param("klassSeminarId") BigInteger klassSeminarId, @Param("studentId") BigInteger studentId);

    /**
     * 获取对当前展示小组的提问问题
     *
     * @param klassSeminarId 班级讨论课id
     * @return 提问列表
     */
    List<Question> getAttendanceQuestionInKlassSeminar(@Param("klassSeminarId") BigInteger klassSeminarId);

    /**
     * 获取提问
     *
     * @param questionId 提问id
     * @return 提问
     */
    Question getQuestion(@Param("questionId") BigInteger questionId);

    /**
     * 获取当前展示组的提问
     *
     * @param attendanceId 展示id
     * @param studentId    学生id
     * @return 提问
     */
    Question getQuestionByAttendanceAndStudent(@Param("attendanceId") BigInteger attendanceId,
                                               @Param("studentId") BigInteger studentId);
}
