package com.xmu.cms.service;

import com.xmu.cms.dao.*;
import com.xmu.cms.entity.*;
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
public class SeminarService {

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

    public void newSeminar(Seminar seminar) throws Exception {
        seminarDao.insertSeminar(seminar);
        List<Klass> klasses = klassDao.getAllKlass(seminar.getCourse().getCourseId());
        for (Klass klass : klasses) {
            klassSeminarDao.insertKlassSeminar(klass.getKlassId(), seminar.getSeminarId());
        }
    }

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

    public List<Seminar> getSeminarsByCourseId(BigInteger courseId) {
        return null;
    }

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

    public void modifyKlassSeminarReportDDL(KlassSeminar klassSeminar) {
        klassSeminarDao.updateKlassSeminarReportDDL(klassSeminar);
    }

    public Seminar getSeminarBySeminarId(BigInteger seminarId) {
        return seminarDao.getSeminarBySeminarId(seminarId);
    }

    public List<Seminar> getAllSeminarInRound(BigInteger roundId) {
        return seminarDao.getAllSeminarByRoundId(roundId);
    }

    public Integer newRound(Round round) {
        return roundDao.newRound(round);
    }

    public KlassSeminar getRunningKlassSeminarByTeacherId(BigInteger teacherId) {
        return klassSeminarDao.getRunningKlassSeminarByTeacherId(teacherId);
    }

    public List<Attendance> getAttendancesInKlassAndSeminar(BigInteger seminarId, BigInteger klassId) {
        return attendanceDao.getAttendancesInKlassAndSeminar(seminarId, klassId);
    }

    public List<Attendance> getAttendancesInKlassSeminar(BigInteger klassSeminarId) {
        return attendanceDao.getAttendancesInKlassSeminar(klassSeminarId);
    }

    public void newAttendance(BigInteger studentId, BigInteger klassSeminarId, Attendance attendance) throws Exception {
        attendance.setKlassSeminar(new KlassSeminar(klassSeminarId));
        KlassSeminar klassSeminar = klassSeminarDao.getKlassSeminar(klassSeminarId);
        Team team = teamDao.getStudentTeamInKlassSeminar(studentId, klassSeminarId);
        if (team == null) {
            throw new Exception("不是该班的成员无法报名");
        }
        Round round = roundDao.getRoundByKlassSeminar(klassSeminarId);
        List<Attendance> attendances = attendanceDao.getTeamAttendanceInRound(team.getTeamId(), round.getRoundId());
        Integer maxNumber = round.getKlassEnrollNumber().get(klassSeminar.getKlass().getKlassId());
        Integer count = 0;
        if (attendances != null) {
            for (Attendance everAttendance : attendances) {
                count++;
            }
        }
        if (count < maxNumber) {
            attendance.setTeam(team);
            attendanceDao.insertAttendance(attendance);
        } else {
            throw new Exception("超出报名上线");
        }
    }

    public Round getRoundByRoundId(BigInteger roundId) {
        return roundDao.getRoundById(roundId);
    }

    public Integer modifyRound(Round round) {
        return roundDao.updateCalType(round);
    }

    public RoundScore getRoundTeamScore(BigInteger roundId, BigInteger teamId) {
        return roundScoreDao.getRoundTeamScore(roundId, teamId);
    }

    public List<RoundScore> getRoundScore(BigInteger roundId) {
        return roundScoreDao.getRoundScore(roundId);
    }

    public SeminarScore getSeminarTeamScore(BigInteger seminarId, BigInteger teamId) {
        return null;
    }

    public List<SeminarScore> getSeminarScore(BigInteger seminarId) {
        return null;
    }

    public List<Question> getQuestionInKlassSeminar(BigInteger klassSeminarId) {
        return questionDao.getQuestionInKlassSeminar(klassSeminarId);
    }

    public Question askQuestion(BigInteger userId, BigInteger attendanceId) {
        //todo
        return null;
    }

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

    public List<Klass> getKlassInSeminar(BigInteger seminarId) {
        return klassSeminarDao.getKlassInSeminar(seminarId);
    }

    public KlassSeminar getKlassSeminarByKlassAndSeminar(BigInteger klassId, BigInteger seminarId) {
        return klassSeminarDao.getKlassSeminarByKlassAndSeminar(klassId, seminarId);
    }

    public Attendance getStudentAttendanceInKlassSeminar(BigInteger studentId, BigInteger klassId, BigInteger seminarId) {
        return attendanceDao.getStudentAttendanceInKlassAndSeminar(studentId, klassId, seminarId);
    }

    public List<Round> getRoundInCourse(UserInfo info, BigInteger courseId) {
        return roundDao.getRoundsInCourse(info, courseId);
    }

    public Question askQuestion(Question question) {
        return questionDao.insertQuestion(question);
    }

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
        Map<BigInteger, Float> questionProbability = new HashMap<>(16);
        for (Question question : noSelectQuestions) {
            Team team = question.getTeam();
            Float questionPro = teamProbability.get(team.getTeamId());
            allProbability += questionPro;
        }
        for (Question question : noSelectQuestions) {
            Team team = question.getTeam();
            Float questionPro = teamProbability.get(team.getTeamId());
            questionProbability.put(question.getQuestionId(), questionPro / allProbability);
        }
        for (Question question : noSelectQuestions) {
            Float questionPro = questionProbability.get(question.getQuestionId());
            if (probability < selectProbability && selectProbability < probability + questionPro) {
                questionDao.selectQuestion(question);
                return question;
            }
            probability += questionPro;
        }
        return null;
    }

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

    public void stopKlassSeminar(BigInteger klassSeminarId) {
        klassSeminarDao.stopKlassSeminar(klassSeminarId);
    }

    public void startKlassSeminar(BigInteger klassSeminarId) {
        List<Attendance> attendances = attendanceDao.getAttendancesInKlassSeminar(klassSeminarId);
        if (attendances != null) {
            attendanceDao.updateAttendancePresent(attendances.get(0));
        }
        klassSeminarDao.startKlassSeminar(klassSeminarId);
    }

    public Attendance getAttendanceByAttendanceId(BigInteger attendanceId) {
        return attendanceDao.getAttendanceByAttendanceId(attendanceId);
    }

    public void attendanceUploadReport(BigInteger attendanceId, String filename) {
        attendanceDao.attendanceUploadReport(attendanceId, filename);
    }

    public void attendanceUploadPPT(BigInteger attendanceId, String filename) {
        attendanceDao.attendanceUploadPPT(attendanceId, filename);
    }

    public void deleteAttendance(BigInteger attendanceId) {
        attendanceDao.deleteAttendance(attendanceId);
    }

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

    public List<Round> getRoundInCourse(BigInteger courseId) {
        return roundDao.getRoundByCourseId(courseId);
    }

    public void modifyTeamSeminarScore(SeminarScore seminarScore) {
        seminarScoreDao.modifyTeamSeminarScore(seminarScore);
        roundScoreDao.updateRoundScore(seminarScore.getKlassSeminar().getKlassSeminarId(), seminarScore.getTeam().getTeamId());
    }

    public void scoreReportScore(BigInteger attendanceId, SeminarScore seminarScore) throws Exception {
        Attendance attendance = attendanceDao.getAttendanceByAttendanceId(attendanceId);
        if (attendance == null) {
            throw new Exception(emptyAttendance);
        }
        seminarScore.setKlassSeminar(new KlassSeminar(attendance.getKlassSeminar().getKlassSeminarId()));
        seminarScore.setTeam(new Team(attendance.getTeam().getTeamId()));
        seminarScoreDao.updateReportScore(seminarScore);
    }

    public void scorePresentationScore(BigInteger attendanceId, SeminarScore seminarScore) throws Exception {
        Attendance attendance = attendanceDao.getAttendanceByAttendanceId(attendanceId);
        if (attendance == null) {
            throw new Exception(emptyAttendance);
        }
        seminarScore.setKlassSeminar(new KlassSeminar(attendance.getKlassSeminar().getKlassSeminarId()));
        seminarScore.setTeam(new Team(attendance.getTeam().getTeamId()));
        seminarScoreDao.updatePresentationScore(seminarScore);
    }

    public Map<String, Object> getStudentRoundScoreAndSeminarScore(BigInteger studentId, BigInteger courseId, BigInteger roundId) {
        Team team = teamDao.getStudentTeamInCourse(studentId, courseId);
        RoundScore roundScore = roundScoreDao.getRoundTeamScore(roundId, team.getTeamId());
        List<SeminarScore> seminarScores = seminarScoreDao.getTeamSeminarScoreInRound(team.getTeamId(), roundId);
        Map<String, Object> score = new HashMap<>();
        score.put("roundScore", roundScore);
        score.put("seminarScore", seminarScores);
        return score;
    }

    public Map<String, Object> getTeamRoundScoreAndSeminarScore(BigInteger teamId, BigInteger courseId, BigInteger roundId) {
        RoundScore roundScore = roundScoreDao.getRoundTeamScore(roundId, teamId);
        List<SeminarScore> seminarScores = seminarScoreDao.getTeamSeminarScoreInRound(teamId, roundId);
        Map<String, Object> score = new HashMap<>();
        score.put("roundScore", roundScore);
        score.put("seminarScore", seminarScores);
        return score;
    }
}
