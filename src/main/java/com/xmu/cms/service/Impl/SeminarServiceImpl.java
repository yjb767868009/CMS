package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.AttendanceDao;
import com.xmu.cms.dao.SeminarDao;
import com.xmu.cms.dao.TeamDao;
import com.xmu.cms.entity.Attendance;
import com.xmu.cms.entity.Seminar;
import com.xmu.cms.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private TeamDao teamDao;

    @Override
    public Map<String, String> newSeminar(Integer roundId, Integer maxTeamNum, String topic, String introduction, Timestamp signStartTime, Timestamp signEndTime, Boolean signOrder, Boolean visible) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = seminarDao.newSeminar(roundId, maxTeamNum, topic, introduction, signStartTime, signEndTime, signOrder, visible);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
    }

    @Override
    public Map<String, String> startClbumSeminar(Integer seminarId, Integer clbumId) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = seminarDao.startClbumSeminar(seminarId, clbumId);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
    }

    @Override
    public Map<String, String> stopClbumSeminar(Integer seminarId, Integer clbumId) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = seminarDao.stopClbumSeminar(seminarId, clbumId);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
    }

    @Override
    public Map<String, String> endClbumSeminar(Integer seminarId, Integer clbumId) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = seminarDao.endClbumSeminar(seminarId, clbumId);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
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
    public Map<String, String> modifySeminar(Integer seminarId, Integer roundId, Integer maxTeamNum, String topic, String introduction, Timestamp signStartTime, Timestamp signEndTime, Boolean signOrder, Boolean visible) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = seminarDao.modifySeminar(seminarId, roundId, maxTeamNum, topic, introduction, signStartTime, signEndTime, signOrder, visible);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
    }

    @Override
    public Seminar getSeminarBySeminarId(Integer seminarId) {
        return seminarDao.getSeminarBySeminarId(seminarId);
    }

    @Override
    public String getStateInClbumSeminar(Integer seminarId, Integer clbumId) {
        return seminarDao.getStateInClbumSeminar(seminarId, clbumId);
    }

    @Override
    public Map<String, String> getPresentationFileInClbumSeminar(Integer clbumSeminarId) {
        Map<String, String> presentationFileMap = new HashMap<String, String>();
        List<Attendance> attendances = attendanceDao.getAttendancesInClbumSeminar(clbumSeminarId);
        for (Attendance attendance : attendances) {
            String attendancePreFile = attendance.getPreFile();
            if (!attendancePreFile.equals("")){
                presentationFileMap.put(attendance.getTeamOrder().toString(), attendancePreFile);
            }else {
                String noFileMessage = teamDao.getTeamByTeamId(attendance.getTeamId()).getTeamName()+"未提交";
                presentationFileMap.put(attendance.getTeamOrder().toString(),noFileMessage);
            }
        }
        return presentationFileMap;
    }

    @Override
    public Seminar getRunningSeminarByTeacherId(Integer teacherId) {
        return seminarDao.getRunningSeminarByTeacherId(teacherId);
    }

}
