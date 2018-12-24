package com.xmu.cms.dao.Impl;

import com.xmu.cms.dao.AttendanceDao;
import com.xmu.cms.entity.Attendance;
import com.xmu.cms.mapper.AttendanceMapper;
import com.xmu.cms.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;

/**
 * @author JuboYu on 2018/12/19.
 * @version 1.0
 */
@Component
public class AttendanceDaoImpl implements AttendanceDao {
    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private TeamMapper teamMapper;

    @Override
    public List<Attendance> getAttendancesInKlassAndSeminar(BigInteger seminarId, BigInteger klassId) {
        return attendanceMapper.getAttendancesInKlassAndSeminar(seminarId, klassId);
    }

    @Override
    public Integer setAttendanceScore(BigInteger attendanceId, Float presentationScore) {
        return attendanceMapper.setAttendanceScore(attendanceId, presentationScore);
    }

    @Override
    public Attendance getStudentAttendanceInKlassSeminar(BigInteger studentId, BigInteger klassId, BigInteger seminarId) {
        return attendanceMapper.getStudentAttendanceInKlassSeminar(studentId, klassId, seminarId);
    }

    @Override
    public List<Attendance> getAttendancesInKlassSeminar(BigInteger klassSeminarId) {
        List<Attendance> attendances = attendanceMapper.getAttendancesInKlassSeminar(klassSeminarId);
        attendances.sort(new Comparator<Attendance>() {
            @Override
            public int compare(Attendance o1, Attendance o2) {
                if (o1.getTeamOrder() < o2.getTeamOrder())
                    return -1;
                else if (o1.getTeamOrder() > o2.getTeamOrder())
                    return 1;
                else
                    return 0;
            }
        });
        return attendances;
    }

    @Override
    public void updateAttendancePresent(Attendance attendance) {
        attendanceMapper.updateAttendancePresent(attendance);
    }
}
