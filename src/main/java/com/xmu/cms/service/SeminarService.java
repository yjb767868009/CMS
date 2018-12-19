package com.xmu.cms.service;

import com.xmu.cms.entity.*;

import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
public interface SeminarService {
    Map<String, String> newSeminar(Seminar seminar);

    Map<String, String> deleteSeminar(Integer seminarId);

    Map<String, String> startKlassSeminar(Integer klassSeminarId);

    Map<String, String> stopKlassSeminar(Integer klassSeminarId);

    Map<String, String> endKlassSeminar(Integer klassSeminarId);

    List<Seminar> getSeminarsByCourseId(Integer courseId);

    Map<String, String> modifySeminar(Seminar seminar);

    Map<String, String> modifySeminarReportDDL(Seminar seminar);

    Seminar getSeminarBySeminarId(Integer seminarId);

    List<Round> getRoundsByCourseId(Integer courseId);

    List<Seminar> getAllSeminarInRound(Integer roundId);

    Map<String, String> newRound(Round round);

    Map<String, String> getPresentationFileInKlassSeminar(Integer klassSeminarId);

    Seminar getRunningSeminarByTeacherId(Integer userId);

    List<Attendance> getAttendancesInSeminar(Integer seminarId);

    Map<String, String> setAttendancePresentationScore(Integer attendanceId, Integer presentationScore);

    Map<String, String> newAttendance(Integer klassSeminarId, Integer teamOrder);

    Round getRoundByRoundId(Integer roundId);

    Map<String, String> modifyRound(Round round);

    RoundScore getRoundTeamScore(Integer roundId, Integer teamId);

    List<RoundScore> getRoundScore(Integer roundId);

    SeminarScore getSeminarTeamScore(Integer seminarId, Integer teamId);

    List<SeminarScore> getSeminarScore(Integer seminarId);
}
