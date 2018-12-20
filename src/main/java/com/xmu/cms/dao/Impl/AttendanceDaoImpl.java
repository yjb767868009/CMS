package com.xmu.cms.dao.Impl;

import com.xmu.cms.dao.AttendanceDao;
import com.xmu.cms.entity.Attendance;
import com.xmu.cms.mapper.AttendanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/19.
 * @version 1.0
 */
@Component
public class AttendanceDaoImpl implements AttendanceDao {
    @Autowired
    private AttendanceMapper attendanceMapper;

    @Override
    public List<Attendance> getAttendancesInSeminar(BigInteger seminarId) {
        return attendanceMapper.getAttendancesInSeminar(seminarId);
    }

    @Override
    public Integer setAttendanceScore(BigInteger attendanceId, Float presentationScore) {
        return attendanceMapper.setAttendanceScore(attendanceId, presentationScore);
    }
}
