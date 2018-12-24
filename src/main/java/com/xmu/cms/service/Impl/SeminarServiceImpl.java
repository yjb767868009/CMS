package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.*;
import com.xmu.cms.entity.*;
import com.xmu.cms.service.SeminarService;
import com.xmu.cms.support.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

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

    @Autowired
    private KlassSeminarDao klassSeminarDao;

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
    public Map<String, String> modifyKlassSeminarReportDDL(KlassSeminar klassSeminar) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = klassSeminarDao.updateKlassSeminarReportDDL(klassSeminar);
        if (count > 0) {
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
    public List<Seminar> getAllSeminarInRound(BigInteger roundId) {
        return seminarDao.getAllSeminarByRoundId(roundId);
    }

    @Override
    public Integer newRound(Round round) {
        return roundDao.newRound(round);
    }

    @Override
    public KlassSeminar getRunningKlassSeminarByTeacherId(BigInteger teacherId) {
        return klassSeminarDao.getRunningKlassSeminarByTeacherId(teacherId);
    }

    @Override
    public List<Attendance> getAttendancesInKlassAndSeminar(BigInteger seminarId, BigInteger klassId) {
        return attendanceDao.getAttendancesInKlassAndSeminar(seminarId, klassId);
    }

    @Override
    public List<Attendance> getAttendancesInKlassSeminar(BigInteger klassSeminarId) {
        return attendanceDao.getAttendancesInKlassSeminar(klassSeminarId);
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

    @Override
    public List<Klass> getKlassInSeminar(BigInteger seminarId) {
        return klassSeminarDao.getKlassInSeminar(seminarId);
    }

    @Override
    public KlassSeminar getKlassSeminarByKlassAndSeminar(BigInteger klassId, BigInteger seminarId) {
        return klassSeminarDao.getKlassSeminarByKlassAndSeminar(klassId, seminarId);
    }

    @Override
    public Attendance getStudentAttendanceInKlassSeminar(BigInteger studentId, BigInteger klassId, BigInteger seminarId) {
        return attendanceDao.getStudentAttendanceInKlassSeminar(studentId, klassId, seminarId);
    }

    @Override
    public List<Round> getRoundInCourse(UserInfo info, BigInteger courseId) {
        return roundDao.getFullRoundsByCourseId(info, courseId);
    }

    @Override
    public void askQuestion(Question question) {
        questionDao.insertQuestion(question);
    }

    @Override
    public Question selectQuestionInKlassSeminar(BigInteger klassSeminarId) {
        List<Question> questions = questionDao.getQuestionInKlassSeminar(klassSeminarId);
        List<Question> noSelectQuestions = questionDao.getNoSelectedQuestionInKlassSeminar(klassSeminarId);
        Map<BigInteger, Float> teamProbability = new HashMap<BigInteger, Float>();
        for (Question question : questions) {
            Team team = question.getTeam();
            Float probability = teamProbability.get(team.getTeamId());
            if (probability == null) {
                teamProbability.put(team.getTeamId(), (float) 1);
            } else {
                teamProbability.put(team.getTeamId(), probability / 2);
            }
        }
        Float probability = (float) 0;
        Float allProbability = (float) 0;
        Float selectProbability = new Random().nextFloat();
        Map<Question, Float> questionProbability = new HashMap<Question, Float>();
        for (Question question : noSelectQuestions) {
            Team team = question.getTeam();
            Float questionPro = teamProbability.get(team.getTeamId());
            allProbability += questionPro;
        }
        for (Question question : noSelectQuestions) {
            Team team = question.getTeam();
            Float questionPro = teamProbability.get(team.getTeamId());
            questionProbability.put(question, questionPro / allProbability);
        }
        for (Question question : noSelectQuestions) {
            Float questionPro = questionProbability.get(question);
            if (probability < selectProbability && selectProbability < probability + questionPro) {
                //questionDao.selectQuestion(question);
                return question;
            }
            probability += questionPro;
        }
        return null;
    }

    @Override
    public void nextAttendance(BigInteger klassSeminarId) {
        List<Attendance> attendances = attendanceDao.getAttendancesInKlassSeminar(klassSeminarId);
        attendances.sort(new Comparator<Attendance>() {
            @Override
            public int compare(Attendance o1, Attendance o2) {
                if (o1.getTeamOrder() < o2.getTeamOrder()) {
                    return -1;
                } else if (o1.getTeamOrder() > o2.getTeamOrder()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        boolean find = false;
        for (Attendance attendance : attendances) {
            if (attendance.getPresent()) {
                attendance.setPresent(false);
                attendanceDao.updateAttendancePresent(attendance);
                find = true;
            }
            if (find) {
                attendance.setPresent(true);
                attendanceDao.updateAttendancePresent(attendance);
                break;
            }
        }
    }

    @Override
    public void stopKlassSeminar(BigInteger klassSeminarId) {
        klassSeminarDao.stopKlassSeminar(klassSeminarId);
    }

    @Override
    public void startKlassSeminar(BigInteger seminarId, BigInteger klassId) {
        klassSeminarDao.startKlassSeminar(seminarId, klassId);
    }
}
