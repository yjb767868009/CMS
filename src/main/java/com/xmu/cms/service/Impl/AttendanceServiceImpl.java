package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.AttendanceDao;
import com.xmu.cms.dao.ClbumSeminarDao;
import com.xmu.cms.dao.TeamDao;
import com.xmu.cms.entity.Attendance;
import com.xmu.cms.entity.ClbumSeminar;
import com.xmu.cms.entity.Team;
import com.xmu.cms.service.AttendanceService;
import com.xmu.cms.support.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private AttendanceDao attendanceDao;

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private ClbumSeminarDao clbumSeminarDao;

    @Override
    public List<Attendance> getAttendancesInSeminar(Integer seminarId) {
        return attendanceDao.getAttendancesInSeminar(seminarId);
    }

    @Override
    public Map<String, String> setAttendancePresentationScore(Integer attendanceId, Integer presentationScore) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = attendanceDao.setAttendanceScore(attendanceId, presentationScore);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
    }

    @Override
    public Map<String, String> newAttendance(Integer clbumSeminarId, Integer teamOrder) {
        Integer studentId = Token.getUserId();
        ClbumSeminar clbumSeminar = clbumSeminarDao.getClbumSeminarById(clbumSeminarId);
        Team team = teamDao.getTeamInClbumByStudentId(clbumSeminar.getClbumId(), studentId);
        return attendanceDao.studentAttendance(team.getTeamId(), clbumSeminarId, teamOrder);
    }

}
