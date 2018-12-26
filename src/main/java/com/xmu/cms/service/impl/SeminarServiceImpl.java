package com.xmu.cms.service.impl;

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

    private static String emptyAttendance = "不存在展示";

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
    private KlassDao klassDao;

    @Autowired
    private KlassSeminarDao klassSeminarDao;

    @Autowired
    private TeamDao teamDao;

    @Override
    public void newSeminar(Seminar seminar) throws Exception {
        seminarDao.insertSeminar(seminar);
        List<Klass> klasses = klassDao.getAllKlass(seminar.getCourse().getCourseId());
        for (Klass klass : klasses) {
            klassSeminarDao.insertKlassSeminar(klass.getKlassId(), seminar.getSeminarId());
        }
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
    public void modifyKlassSeminarReportDDL(KlassSeminar klassSeminar) {
        klassSeminarDao.updateKlassSeminarReportDDL(klassSeminar);
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
    public void newAttendance(BigInteger studentId, Attendance attendance) {
        KlassSeminar klassSeminar = attendance.getKlassSeminar();
        Team team = teamDao.getStudentTeamInKlassSeminar(studentId, klassSeminar.getKlassSeminarId());
        attendance.setTeam(team);
        attendanceDao.insertAttendance(attendance);
    }

    @Override
    public Round getRoundByRoundId(BigInteger roundId) {
        return roundDao.getRoundById(roundId);
    }

    @Override
    public Integer modifyRound(Round round) {
        return roundDao.updateCalType(round);
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
    public Question askQuestion(Question question) {
        return questionDao.insertQuestion(question);
    }

    @Override
    public Question selectQuestionInKlassSeminar(BigInteger klassSeminarId) {
        List<Question> questions = questionDao.getQuestionInKlassSeminar(klassSeminarId);
        List<Question> noSelectQuestions = questionDao.getNoSelectedQuestionInKlassSeminar(klassSeminarId);
        Map<BigInteger, Float> teamProbability = new HashMap<BigInteger, Float>(16);
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
        Map<Question, Float> questionProbability = new HashMap<Question, Float>(16);
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
                questionDao.selectQuestion(question);
                return question;
            }
            probability += questionPro;
        }
        return null;
    }

    @Override
    public Attendance nextAttendance(BigInteger klassSeminarId) {
        List<Attendance> attendances = attendanceDao.getAttendancesInKlassSeminar(klassSeminarId);
        boolean find = false;
        for (Attendance attendance : attendances) {
            if (find) {
                attendance.setPresent(true);
                attendanceDao.updateAttendancePresent(attendance);
                return attendance;
            }
            if (attendance.getPresent()) {
                attendance.setPresent(false);
                attendanceDao.updateAttendancePresent(attendance);
                find = true;
            }
        }
        return null;
    }

    @Override
    public void stopKlassSeminar(BigInteger klassSeminarId) {
        klassSeminarDao.stopKlassSeminar(klassSeminarId);
    }

    @Override
    public void startKlassSeminar(BigInteger klassSeminarId) {
        List<Attendance> attendances = attendanceDao.getAttendancesInKlassSeminar(klassSeminarId);
        if (attendances != null) {
            attendanceDao.updateAttendancePresent(attendances.get(0));
        }
        klassSeminarDao.startKlassSeminar(klassSeminarId);
    }

    @Override
    public Attendance getAttendanceByAttendanceId(BigInteger attendanceId) {
        return attendanceDao.getAttendanceByAttendanceId(attendanceId);
    }

    @Override
    public void attendanceUploadReport(BigInteger attendanceId, String filename) {
        attendanceDao.attendanceUploadReport(attendanceId, filename);
    }

    @Override
    public void attendanceUploadPPT(BigInteger attendanceId, String filename) {
        attendanceDao.attendanceUploadPPT(attendanceId, filename);
    }

    @Override
    public void deleteAttendance(BigInteger attendanceId) {
        attendanceDao.deleteAttendance(attendanceId);
    }

    @Override
    public List<Map<String, Object>> getRoundScoreInCourse(BigInteger courseId, BigInteger roundId) {
        List<Team> teams = teamDao.getSimpleTeamInCourse(courseId);
        List<Map<String, Object>> allScore = new ArrayList<>();
        for (Team team : teams) {
            Map<String, Object> score = new HashMap<>(2);
            RoundScore roundScore = roundScoreDao.getRoundTeamScore(roundId, team.getTeamId());
            score.put("team", team);
            score.put("score", roundScore.getTotalScore());
            allScore.add(score);
        }
        return allScore;
    }

    @Override
    public List<Round> getRoundInCourse(BigInteger courseId) {
        return roundDao.getRoundByCourseId(courseId);
    }

    @Override
    public void modifyTeamSeminarScore(SeminarScore seminarScore) {
        seminarScoreDao.modifyTeamSeminarScore(seminarScore);
        roundScoreDao.updateRoundScore(seminarScore.getKlassSeminar().getKlassSeminarId(), seminarScore.getTeam().getTeamId());
    }

    @Override
    public void scoreReportScore(BigInteger attendanceId, SeminarScore seminarScore) throws Exception {
        Attendance attendance = attendanceDao.getAttendanceByAttendanceId(attendanceId);
        if (attendance == null) {
            throw new Exception(emptyAttendance);
        }
        seminarScore.setKlassSeminar(new KlassSeminar(attendance.getKlassSeminar().getKlassSeminarId()));
        seminarScore.setTeam(new Team(attendance.getTeam().getTeamId()));
        seminarScoreDao.updateReportScore(seminarScore);
    }

    @Override
    public void scorePresentationScore(BigInteger attendanceId, SeminarScore seminarScore) throws Exception {
        Attendance attendance = attendanceDao.getAttendanceByAttendanceId(attendanceId);
        if (attendance == null) {
            throw new Exception(emptyAttendance);
        }
        seminarScore.setKlassSeminar(new KlassSeminar(attendance.getKlassSeminar().getKlassSeminarId()));
        seminarScore.setTeam(new Team(attendance.getTeam().getTeamId()));
        seminarScoreDao.updatePresentationScore(seminarScore);
    }
}
