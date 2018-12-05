package com.xmu.cms.service;

import com.xmu.cms.entity.Attendance;

import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
public interface AttendanceService {
    List<Attendance> getAttendancesInSeminar(Integer seminarId);

    Map<String, String> setAttendancePresentationScore(Integer attendanceId, Integer presentationScore);

    Map<String, String> newAttendance(Integer clbumSeminarId, Integer teamOrder);
}
