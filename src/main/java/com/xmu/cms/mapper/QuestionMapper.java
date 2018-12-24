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
     * 获取班级讨论课的所有提问,未选中
     *
     * @param klassSeminarId 班级讨论课id
     * @return 提问列表
     */
    List<Question> getQuestionInKlassSeminar(BigInteger klassSeminarId);

    /**
     * 获取所有未选择的提问
     *
     * @param klassSeminarId 班级讨论课id
     * @return 提问列表
     */
    List<Question> getNoSelectedQuestionInKlassSeminar(BigInteger klassSeminarId);

    /**
     * 给提问打分
     *
     * @param question 提问信息
     * @return 修改信息
     */
    Integer scoreQuestion(Question question);

    /**
     * 发出提问
     *
     * @param question 新的提问
     */
    void insertQuestion(@Param("question") Question question);
}
