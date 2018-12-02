package com.xmu.cms.service;

import com.xmu.cms.entity.Attendance;

import java.util.List;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
public interface AttendanceService {
    List<Attendance> getAttendancesInSeminar(Integer seminarId);

    String setAttendancePresentationScore(Integer attendanceId, Integer presentationScore);
}
