package com.xmu.cms.service;

import com.xmu.cms.entity.Attendance;
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

    Map<String, String> startClbumSeminar(Integer seminarId, Integer clbumId);

    Map<String, String> stopClbumSeminar(Integer seminarId, Integer clbumId);

    Map<String, String> endClbumSeminar(Integer seminarId, Integer clbumId);

    Attendance getNextAttendance(Integer seminarId);

    List<Seminar> getSeminarsByCourseId(Integer courseId);

    Map<String, String> modifySeminar(Integer seminarId, Integer roundId, Integer maxTeamNum, String topic, String introduction, Timestamp signStartTime, Timestamp signEndTime, Boolean signOrder, Boolean visible);

    Seminar getSeminarBySeminarId(Integer seminarId);

    Seminar getClbumSeminar(Integer seminarId, Integer clbumId);
}
