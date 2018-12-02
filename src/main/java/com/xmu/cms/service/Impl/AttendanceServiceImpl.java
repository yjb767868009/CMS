package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.AttendanceDao;
import com.xmu.cms.entity.Attendance;
import com.xmu.cms.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private AttendanceDao attendanceDao;

    @Override
    public List<Attendance> getAttendancesInSeminar(Integer seminarId) {
        return attendanceDao.getAttendancesInSeminar(seminarId);
    }

    @Override
    public String setAttendancePresentationScore(Integer attendanceId, Integer presentationScore) {
        Integer count = attendanceDao.setAttendanceScore(attendanceId, presentationScore);
        if (count == 1) {
            return "Success";
        } else {
            return "Set Score Error";
        }
    }

}
