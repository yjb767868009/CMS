package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.SeminarDao;
import com.xmu.cms.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
public class SeminarServiceImpl implements SeminarService {

    @Autowired
    private SeminarDao seminarDao;

    @Override
    public String newSeminarTurningClass(Integer seminarId, Integer turningClassId, Integer roundId, Integer maxTeamNum, Date signStartTime, Date signEndTime, Boolean signOrder) {
        Integer count = seminarDao.newSeminarTurningClass(seminarId, turningClassId, roundId, maxTeamNum, signStartTime, signEndTime, signOrder);
        if (count == 0) {
            return "Insert Error";
        } else {
            return "Success";
        }
    }

    @Override
    public String modifySeminarTurningClass(Integer seminarId, Integer turningClassId, Integer roundId, Integer maxTeamNum, Date signStartTime, Date signEndTime, Date reportEndTime, Integer status, Boolean signOrder) {
        Integer count = seminarDao.modifySeminarTurningClass(seminarId, turningClassId, roundId, maxTeamNum, signStartTime, signEndTime, signOrder);
        if (count == 0) {
            return "Update Error";
        } else {
            return "Success";
        }
    }

    @Override
    public String startSeminarTurningClass(Integer seminarId, Integer turningClassId) {
        Integer count = seminarDao.startSeminarTurningClass(seminarId, turningClassId);
        if (count == 1) {
            return "Success";
        } else {
            return "Start SeminarInfo Error";
        }
    }

    @Override
    public String stopSeminarTurningClass(Integer seminarId, Integer turningClassId) {
        Integer count = seminarDao.stopSeminarTurningClass(seminarId, turningClassId);
        if (count == 1) {
            return "Success";
        } else {
            return "Stop SeminarInfo Error";
        }
    }

    @Override
    public String endSeminarTurningClass(Integer seminarId, Integer turningClassId) {
        Integer count = seminarDao.endSeminarTurningClass(seminarId, turningClassId);
        if (count == 1) {
            return "Success";
        } else {
            return "End SeminarInfo Error";
        }
    }
}
