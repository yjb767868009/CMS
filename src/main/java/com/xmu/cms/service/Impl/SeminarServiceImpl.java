package com.xmu.cms.service.Impl;

import com.xmu.cms.mapper.AttendanceMapper;
import com.xmu.cms.mapper.SeminarMapper;
import com.xmu.cms.mapper.TeamMapper;
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
    private SeminarMapper seminarMapper;

    @Autowired
    private AttendanceMapper attendanceDao;

    @Autowired
    private TeamMapper teamMapper;

    @Override
    public Map<String, String> newSeminar(Integer roundId, Integer maxTeamNum, String topic, String introduction, Timestamp signStartTime, Timestamp signEndTime, Boolean signOrder, Boolean visible) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = seminarMapper.newSeminar(roundId, maxTeamNum, topic, introduction, signStartTime, signEndTime, signOrder, visible);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
    }

    @Override
    public Map<String, String> startClbumSeminar(Integer clbumSeminarId) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = seminarMapper.startClbumSeminar(clbumSeminarId);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
    }

    @Override
    public Map<String, String> stopClbumSeminar(Integer clbumSeminarId) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = seminarMapper.stopClbumSeminar(clbumSeminarId);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
    }

    @Override
    public Map<String, String> endClbumSeminar(Integer clbumSeminarId) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = seminarMapper.endClbumSeminar(clbumSeminarId);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
    }

    @Override
    public List<Seminar> getSeminarsByCourseId(Integer courseId) {
        return null;
    }

    @Override
    public Map<String, String> modifySeminar(Integer seminarId, Integer roundId, Integer maxTeamNum, String topic, String introduction, Timestamp signStartTime, Timestamp signEndTime, Boolean signOrder, Boolean visible) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = seminarMapper.modifySeminar(seminarId, roundId, maxTeamNum, topic, introduction, signStartTime, signEndTime, signOrder, visible);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
    }

    @Override
    public Seminar getSeminarBySeminarId(Integer seminarId) {
        return seminarMapper.getSeminarBySeminarId(seminarId);
    }

    @Override
    public Map<String, String> getPresentationFileInClbumSeminar(Integer clbumSeminarId) {
        Map<String, String> presentationFileMap = new HashMap<String, String>();
        List<Attendance> attendances = attendanceDao.getAttendancesInClbumSeminar(clbumSeminarId);
        for (Attendance attendance : attendances) {
            String attendancePresentationFile = attendance.getPresentationFile();
            if (!attendancePresentationFile.equals("")) {
                presentationFileMap.put(attendance.getTeam().getTeamName(), attendancePresentationFile);
            } else {
                String noFileMessage = attendance.getTeam().getTeamName() + "未提交";
                presentationFileMap.put(attendance.getTeam().getTeamName(), noFileMessage);
            }
        }
        return presentationFileMap;
    }

    @Override
    public Seminar getRunningSeminarByTeacherId(Integer teacherId) {
        return seminarMapper.getRunningSeminarByTeacherId(teacherId);
    }

}
