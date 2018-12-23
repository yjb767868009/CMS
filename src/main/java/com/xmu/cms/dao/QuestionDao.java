package com.xmu.cms.dao;

import com.xmu.cms.entity.Question;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/20.
 * @version 1.0
 */
public interface QuestionDao {
    List<Question> getQuestionInKlassSeminar(BigInteger klassSeminarId);

    List<Question> getNoSelectedQuestionInKlassSeminar(BigInteger klassSeminar);

    Integer scoreQuestion(Question question);

    void insertQuestion(Question question);
}
