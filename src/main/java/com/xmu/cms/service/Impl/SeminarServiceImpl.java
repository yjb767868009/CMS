package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.*;
import com.xmu.cms.entity.*;
import com.xmu.cms.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@Service
public class SeminarServiceImpl implements SeminarService {

    @Autowired
    private SeminarDao seminarDao;

    @Autowired
    private AttendanceDao attendanceDao;

    @Autowired
    private RoundDao roundDao;

    @Autowired
    private RoundScoreDao roundScoreDao;

    @Autowired
    private SeminarScoreDao seminarScoreDao;

    @Autowired
    private QuestionDao questionDao;

    @Override
    public Map<String, String> newSeminar(Seminar seminar) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = seminarDao.insertSeminar(seminar);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
    }

    @Override
    public Map<String, String> deleteSeminar(BigInteger seminarId) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = seminarDao.deleteSeminar(seminarId);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
    }

    @Override
    public List<Seminar> getSeminarsByCourseId(BigInteger courseId) {
        return null;
    }

    @Override
    public Map<String, String> modifySeminar(Seminar seminar) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = seminarDao.modifySeminar(seminar);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
    }

    @Override
    public Map<String, String> modifySeminarReportDDL(Seminar seminar) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = seminarDao.modifySeminarReportDDL(seminar);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
    }

    @Override
    public Seminar getSeminarBySeminarId(BigInteger seminarId) {
        return seminarDao.getSeminarBySeminarId(seminarId);
    }

    @Override
    public List<Round> getRoundsByCourseId(BigInteger courseId) {
        return roundDao.getRoundsByCourseId(courseId);
    }

    @Override
    public List<Seminar> getAllSeminarInRound(BigInteger roundId) {
        return seminarDao.getAllSeminarByRoundId(roundId);
    }

    @Override
    public Map<String, String> newRound(Round round) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = roundDao.newRound(round);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
    }

    @Override
    public Seminar getRunningSeminarByTeacherId(BigInteger teacherId) {
        return seminarDao.getRunningSeminarByTeacherId(teacherId);
    }

    @Override
    public List<Attendance> getAttendancesInSeminar(BigInteger seminarId) {
        return attendanceDao.getAttendancesInSeminar(seminarId);
    }

    @Override
    public Map<String, String> setAttendancePresentationScore(BigInteger attendanceId, Float presentationScore) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = attendanceDao.setAttendanceScore(attendanceId, presentationScore);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
    }

    @Override
    public Map<String, String> newAttendance(BigInteger klassSeminarId, BigInteger teamOrder) {
        //todo
        return null;
    }

    @Override
    public Round getRoundByRoundId(BigInteger roundId) {
        return roundDao.getRoundById(roundId);
    }

    @Override
    public Map<String, String> modifyRound(Round round) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = roundDao.updateCalType(round);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
    }

    @Override
    public RoundScore getRoundTeamScore(BigInteger roundId, BigInteger teamId) {
        return roundScoreDao.getRoundTeamScore(roundId, teamId);
    }

    @Override
    public List<RoundScore> getRoundScore(BigInteger roundId) {
        return roundScoreDao.getRoundScore(roundId);
    }

    @Override
    public SeminarScore getSeminarTeamScore(BigInteger seminarId, BigInteger teamId) {
        return null;
    }

    @Override
    public List<SeminarScore> getSeminarScore(BigInteger seminarId) {
        return null;
    }

    @Override
    public List<Question> getQuestionInKlassSeminar(BigInteger klassSeminarId) {
        return questionDao.getQuestionInKlassSeminar(klassSeminarId);
    }

    @Override
    public Question askQuestion(BigInteger userId, BigInteger attendanceId) {
        //todo
        return null;
    }

    @Override
    public Map<String, String> scoreQuestion(Question question) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = questionDao.scoreQuestion(question);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
    }


}
