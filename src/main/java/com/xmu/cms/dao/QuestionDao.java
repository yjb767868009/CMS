package com.xmu.cms.dao;

import com.xmu.cms.entity.Attendance;
import com.xmu.cms.entity.Question;
import com.xmu.cms.entity.Team;
import com.xmu.cms.mapper.AttendanceMapper;
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

    @Autowired
    private AttendanceMapper attendanceMapper;

    public List<Question> getQuestionInKlassSeminar(BigInteger klassSeminarId) {
        return questionMapper.getQuestionInKlassSeminar(klassSeminarId);
    }

    public List<Question> getNoSelectedQuestionInKlassSeminar(BigInteger klassSeminar) {
        return questionMapper.getNoSelectedQuestionInKlassSeminar(klassSeminar);
    }

    public Question scoreQuestion(Question question) {
        questionMapper.scoreQuestion(question);
        return questionMapper.getQuestion(question.getQuestionId());
    }

    public Question insertQuestion(Question question) throws Exception {
        BigInteger klassSeminarId = question.getKlassSeminar().getKlassSeminarId();
        BigInteger studentId = question.getStudent().getStudentId();
        Team team = teamMapper.getStudentTeamInKlassSeminar(studentId, klassSeminarId);
        Attendance attendance = attendanceMapper.getAttendanceByAttendanceId(question.getAttendance().getAttendanceId());
        if (attendance.getTeam().getTeamId().equals(team.getTeamId())) {
            throw new Exception("请勿给自己组提问");
        }
        Question findQuestion = questionMapper.getQuestionByAttendanceAndStudent(attendance.getAttendanceId(), studentId);
        if (findQuestion == null) {
            question.setTeam(team);
            questionMapper.insertQuestion(question);
            return questionMapper.getQuestionByKlassSeminarAndStudent(klassSeminarId, studentId);
        } else {
            throw new Exception("请勿重复提问");
        }
    }

    public void selectQuestion(Question question) {
        questionMapper.selectQuestion(question);
    }

    public List<Question> getAttendanceQuestionInKlassSeminar(BigInteger klassSeminarId) {
        return questionMapper.getAttendanceQuestionInKlassSeminar(klassSeminarId);
    }
}
