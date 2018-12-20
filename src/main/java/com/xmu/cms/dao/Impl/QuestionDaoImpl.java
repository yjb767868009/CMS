package com.xmu.cms.dao.Impl;

import com.xmu.cms.dao.QuestionDao;
import com.xmu.cms.entity.Question;
import com.xmu.cms.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/20.
 * @version 1.0
 */
@Component
public class QuestionDaoImpl implements QuestionDao {
    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public List<Question> getQuestionInKlassSeminar(BigInteger klassSeminarId) {
        return questionMapper.getQuestionInKlassSeminar(klassSeminarId);
    }

    @Override
    public List<Question> getNoSelectedQuestionInKlassSeminar(BigInteger klassSeminar) {
        return questionMapper.getNoSelectedQuestionInKlassSeminar(klassSeminar);
    }

    @Override
    public Integer scoreQuestion(Question question) {
        return questionMapper.scoreQuestion(question);
    }
}
