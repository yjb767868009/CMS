package com.xmu.cms.dao;

import com.xmu.cms.entity.Attendance;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/19.
 * @version 1.0
 */
public interface AttendanceDao {
    List<Attendance> getAttendancesInSeminar(BigInteger seminarId);

    Integer setAttendanceScore(BigInteger attendanceId, Float presentationScore);
}
