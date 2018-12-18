package com.xmu.cms.service;

import com.xmu.cms.entity.Attendance;
import com.xmu.cms.entity.Round;
import com.xmu.cms.entity.Seminar;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
public interface SeminarService {
    Map<String, String> newSeminar(Integer roundId, Integer maxTeamNum, String topic, String introduction, Timestamp signStartTime, Timestamp signEndTime, Boolean signOrder, Boolean visible);

    Map<String, String> startKlassSeminar(Integer klassSeminarId);

    Map<String, String> stopKlassSeminar(Integer klassSeminarId);

    Map<String, String> endKlassSeminar(Integer klassSeminarId);

    List<Seminar> getSeminarsByCourseId(Integer courseId);

    Map<String, String> modifySeminar(Integer seminarId, Integer roundId, Integer maxTeamNum, String topic, String introduction, Timestamp signStartTime, Timestamp signEndTime, Boolean signOrder, Boolean visible);

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
}
