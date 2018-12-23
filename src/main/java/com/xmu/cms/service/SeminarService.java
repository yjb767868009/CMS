package com.xmu.cms.service;

import com.xmu.cms.entity.*;
import com.xmu.cms.support.UserInfo;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
public interface SeminarService {
    Map<String, String> newSeminar(Seminar seminar);

    Map<String, String> deleteSeminar(BigInteger seminarId);

    List<Seminar> getSeminarsByCourseId(BigInteger courseId);

    Map<String, String> modifySeminar(Seminar seminar);

    Map<String, String> modifyKlassSeminarReportDDL(KlassSeminar klassSeminar);

    Seminar getSeminarBySeminarId(BigInteger seminarId);

    List<Seminar> getAllSeminarInRound(BigInteger roundId);

    Map<String, String> newRound(Round round);

    Seminar getRunningSeminarByTeacherId(BigInteger userId);

    List<Attendance> getAttendancesInKlassAndSeminar(BigInteger seminarId, BigInteger klassId);

    List<Attendance> getAttendancesInKlassSeminar(BigInteger klassSeminarId);

    Map<String, String> setAttendancePresentationScore(BigInteger attendanceId, Float presentationScore);

    Map<String, String> newAttendance(BigInteger klassSeminarId, BigInteger teamOrder);

    Round getRoundByRoundId(BigInteger roundId);

    Map<String, String> modifyRound(Round round);

    RoundScore getRoundTeamScore(BigInteger roundId, BigInteger teamId);

    List<RoundScore> getRoundScore(BigInteger roundId);

    SeminarScore getSeminarTeamScore(BigInteger seminarId, BigInteger teamId);

    List<SeminarScore> getSeminarScore(BigInteger seminarId);

    List<Question> getQuestionInKlassSeminar(BigInteger klassSeminarId);

    Question askQuestion(BigInteger userId, BigInteger attendanceId);

    Map<String, String> scoreQuestion(Question question);

    List<Klass> getKlassInSeminar(BigInteger seminarId);

    KlassSeminar getKlassSeminarByKlassAndSeminar(BigInteger klassId, BigInteger seminarId);

    Attendance getStudentAttendanceInKlassSeminar(BigInteger studentId, BigInteger klassId, BigInteger seminarId);

    List<Round> getRoundInCourse(UserInfo info, BigInteger courseId);

    void askQuestion(Question question);

    Question selectQuestionInAttendance(BigInteger klassSeminarId);
}
