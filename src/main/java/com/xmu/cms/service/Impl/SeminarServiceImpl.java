package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.AttendanceDao;
import com.xmu.cms.dao.SeminarDao;
import com.xmu.cms.entity.Attendance;
import com.xmu.cms.entity.Seminar;
import com.xmu.cms.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@Service
public class SeminarServiceImpl implements SeminarService {

    @Autowired
    private SeminarDao seminarDao;

    @Autowired
    private AttendanceDao attendanceDao;

    @Override
    public String newSeminar(Integer roundId, Integer maxTeamNum, String topic, String introduction, Timestamp signStartTime, Timestamp signEndTime, Boolean signOrder, Boolean visible) {
        Integer count = seminarDao.newSeminar(roundId, maxTeamNum, topic, introduction, signStartTime, signEndTime, signOrder, visible);
        if (count == 1) {
            return "Success";
        } else {
            return "New a seminar error";
        }
    }

    @Override
    public String startClbumSeminar(Integer seminarId, Integer clbumId) {
        Integer count = seminarDao.startClbumSeminar(seminarId, clbumId);
        if (count == 1) {
            return "Success";
        } else {
            return "Start Seminar Error";
        }
    }

    @Override
    public String stopClbumSeminar(Integer seminarId, Integer clbumId) {
        Integer count = seminarDao.stopClbumSeminar(seminarId, clbumId);
        if (count == 1) {
            return "Success";
        } else {
            return "Stop Seminar Error";
        }
    }

    @Override
    public String endClbumSeminar(Integer seminarId, Integer clbumId) {
        Integer count = seminarDao.endClbumSeminar(seminarId, clbumId);
        if (count == 1) {
            return "Success";
        } else {
            return "End Seminar Error";
        }
    }

    @Override
    public Attendance getNextAttendance(Integer seminarId) {
        Integer selectNum = seminarDao.getAttendanceNo(seminarId);
        List<Attendance> attendances = attendanceDao.getAttendancesInSeminar(seminarId);
        for (Attendance attendance : attendances) {
            if (attendance.getTeamOrder() > selectNum) {
                return attendance;
            }
        }
        return null;
    }

    @Override
    public List<Seminar> getSeminarsByCourseId(Integer courseId) {
        return null;
    }

    @Override
    public String modifySeminar(Integer seminarId, Integer roundId, Integer maxTeamNum, String topic, String introduction, Timestamp signStartTime, Timestamp signEndTime, Boolean signOrder, Boolean visible) {
        Integer count = seminarDao.modifySeminar(seminarId, roundId, maxTeamNum, topic, introduction, signStartTime, signEndTime, signOrder, visible);
        if (count == 1) {
            return "Success";
        } else {
            return "Modify Seminar Error";
        }
    }

    @Override
    public Seminar getSeminarBySeminarId(Integer seminarId) {
        return seminarDao.getSeminarBySeminarId(seminarId);
    }

    @Override
    public Seminar getClbumSeminar(Integer seminarId, Integer clbumId) {
        return null;
    }


}
