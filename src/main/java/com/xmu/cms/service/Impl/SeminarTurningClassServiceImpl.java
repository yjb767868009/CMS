package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.SeminarTurningClassDao;
import com.xmu.cms.service.SeminarTurningClassService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
public class SeminarTurningClassServiceImpl implements SeminarTurningClassService {

    @Autowired
    private SeminarTurningClassDao seminarTurningClassDao;

    @Override
    public String newSeminarTurningClass(Integer seminarId, Integer turningClassId, Integer roundId, Integer maxTeamNum, Date signStartTime, Date signEndTime, Boolean signOrder) {
        Integer count = seminarTurningClassDao.newSeminarTurningClass(seminarId, turningClassId, roundId, maxTeamNum, signStartTime, signEndTime, signOrder);
        if (count == 0) {
            return "Insert Error";
        } else {
            return "Success";
        }
    }

    @Override
    public String modifySeminarTurningClass(Integer seminarId, Integer turningClassId, Integer roundId, Integer maxTeamNum, Date signStartTime, Date signEndTime, Date reportEndTime, Integer status, Boolean signOrder) {
        Integer count = seminarTurningClassDao.modifySeminarTurningClass(seminarId, turningClassId, roundId, maxTeamNum, signStartTime, signEndTime, signOrder);
        if (count == 0) {
            return "Update Error";
        } else {
            return "Success";
        }
    }

    @Override
    public String startSeminarTurningClass(Integer seminarId, Integer turningClassId) {
        Integer count = seminarTurningClassDao.startSeminarTurningClass(seminarId, turningClassId);
        if (count == 1) {
            return "Success";
        } else {
            return "Start Seminar Error";
        }
    }

    @Override
    public String stopSeminarTurningClass(Integer seminarId, Integer turningClassId) {
        Integer count = seminarTurningClassDao.stopSeminarTurningClass(seminarId, turningClassId);
        if (count == 1) {
            return "Success";
        } else {
            return "Stop Seminar Error";
        }
    }

    @Override
    public String endSeminarTurningClass(Integer seminarId, Integer turningClassId) {
        Integer count = seminarTurningClassDao.endSeminarTurningClass(seminarId, turningClassId);
        if (count == 1) {
            return "Success";
        } else {
            return "End Seminar Error";
        }
    }

    @Override
    public String continueSeminarTurningClass(Integer seminarId, Integer turningClassId) {
        Integer count = seminarTurningClassDao.continueSeminarTurningClass(seminarId, turningClassId);
        if (count == 1) {
            return "Success";
        } else {
            return "Continue Seminar Error";
        }
    }
}
