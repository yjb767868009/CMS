package com.xmu.cms.dao;

import com.xmu.cms.entity.Attendance;
import com.xmu.cms.mapper.AttendanceMapper;
import com.xmu.cms.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/19.
 * @version 1.0
 */
@Component
public class AttendanceDao {
    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private TeamMapper teamMapper;

    public List<Attendance> getAttendancesInKlassAndSeminar(BigInteger seminarId, BigInteger klassId) {
        return attendanceMapper.getAttendancesInKlassAndSeminar(seminarId, klassId);
    }

    public Attendance getStudentAttendanceInKlassSeminar(BigInteger studentId, BigInteger klassId, BigInteger seminarId) {
        return attendanceMapper.getStudentAttendanceInKlassSeminar(studentId, klassId, seminarId);
    }

    public List<Attendance> getAttendancesInKlassSeminar(BigInteger klassSeminarId) {
        List<Attendance> attendances = attendanceMapper.getAttendancesInKlassSeminar(klassSeminarId);
        attendances.sort((o1, o2) -> {
            if (o1.getTeamOrder() < o2.getTeamOrder()) {
                return -1;
            } else if (o1.getTeamOrder() > o2.getTeamOrder()) {
                return 1;
            } else {
                return 0;
            }
        });
        return attendances;
    }

    public void updateAttendancePresent(Attendance attendance) {
        attendanceMapper.updateAttendancePresent(attendance);
    }

    public Attendance getAttendanceByAttendanceId(BigInteger attendanceId) {
        return attendanceMapper.getAttendanceByAttendanceId(attendanceId);
    }

    public void attendanceUploadReport(BigInteger attendanceId, String filename) {
        attendanceMapper.attendanceUploadReport(attendanceId, filename);
    }

    public void attendanceUploadPPT(BigInteger attendanceId, String filename) {
        attendanceMapper.attendanceUploadPPT(attendanceId, filename);
    }

    public void deleteAttendance(BigInteger attendanceId) {
        attendanceMapper.deleteAttendance(attendanceId);
    }

    public void insertAttendance(Attendance attendance) {
        attendance.setPresent(false);
        attendanceMapper.insertAttendance(attendance);
    }
}
