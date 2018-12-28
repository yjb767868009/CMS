package com.xmu.cms.dao;

import com.xmu.cms.entity.Question;
import com.xmu.cms.entity.Team;
import com.xmu.cms.mapper.QuestionMapper;
import com.xmu.cms.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/20.
 * @version 1.0
 */
@Component
public class QuestionDao {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private TeamMapper teamMapper;

    public List<Question> getQuestionInKlassSeminar(BigInteger klassSeminarId) {
        return questionMapper.getQuestionInKlassSeminar(klassSeminarId);
    }

    public List<Question> getNoSelectedQuestionInKlassSeminar(BigInteger klassSeminar) {
        return questionMapper.getNoSelectedQuestionInKlassSeminar(klassSeminar);
    }

    public Integer scoreQuestion(Question question) {
        return questionMapper.scoreQuestion(question);
    }

    public Question insertQuestion(Question question) {
        BigInteger klassSeminarId = question.getKlassSeminar().getKlassSeminarId();
        BigInteger studentId = question.getStudent().getStudentId();
        Question findQuestion = questionMapper.getQuestionByKlassSeminarAndStudent(klassSeminarId, studentId);
        if (findQuestion == null) {
            Team team = teamMapper.getStudentTeamInKlassSeminar(studentId, klassSeminarId);
            question.setTeam(team);
            questionMapper.insertQuestion(question);
            return questionMapper.getQuestionByKlassSeminarAndStudent(klassSeminarId, studentId);
        }
        return null;
    }

    public void selectQuestion(Question question) {
        questionMapper.selectQuestion(question);
    }

    public List<Question> getAttendanceQuestionInKlassSeminar(BigInteger klassSeminarId) {
        return questionMapper.getAttendanceQuestionInKlassSeminar(klassSeminarId);
    }
}
