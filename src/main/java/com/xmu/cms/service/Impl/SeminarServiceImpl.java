package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.PresentationDao;
import com.xmu.cms.dao.SeminarDao;
import com.xmu.cms.entity.Presentation;
import com.xmu.cms.entity.Seminar;
import com.xmu.cms.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    private PresentationDao presentationDao;

    @Override
    public String newSeminar(Integer seminarId, Integer turningClassId, Integer roundId, Integer maxTeamNum, Date signStartTime, Date signEndTime, Boolean signOrder) {
        Integer count = seminarDao.newSeminar(seminarId, turningClassId, roundId, maxTeamNum, signStartTime, signEndTime, signOrder);
        if (count == 0) {
            return "Insert Error";
        } else {
            return "Success";
        }
    }

    @Override
    public String modifySeminar(Integer seminarId, Integer turningClassId, Integer roundId, Integer maxTeamNum, Date signStartTime, Date signEndTime, Date reportEndTime, Integer status, Boolean signOrder) {
        Integer count = seminarDao.modifySeminar(seminarId, turningClassId, roundId, maxTeamNum, signStartTime, signEndTime, signOrder);
        if (count == 0) {
            return "Update Error";
        } else {
            return "Success";
        }
    }

    @Override
    public String startSeminar(Integer seminarId, Integer turningClassId) {
        Integer count = seminarDao.startSeminar(seminarId, turningClassId);
        if (count == 1) {
            return "Success";
        } else {
            return "Start SeminarInfo Error";
        }
    }

    @Override
    public String stopSeminar(Integer seminarId, Integer turningClassId) {
        Integer count = seminarDao.stopSeminar(seminarId, turningClassId);
        if (count == 1) {
            return "Success";
        } else {
            return "Stop SeminarInfo Error";
        }
    }

    @Override
    public String endSeminar(Integer seminarId, Integer turningClassId) {
        Integer count = seminarDao.endSeminar(seminarId, turningClassId);
        if (count == 1) {
            return "Success";
        } else {
            return "End SeminarInfo Error";
        }
    }

    @Override
    public Presentation getNextPresentation(Integer seminarId) {
        Integer selectNum = seminarDao.getPresentationNo(seminarId);
        List<Presentation> presentations = presentationDao.getPresentationsInSeminar(seminarId);
        for(Presentation presentation : presentations){
            if (presentation.getTeamOrder()>selectNum){
                return  presentation;
            }
        }
        return null;
    }

    @Override
    public Seminar getSeminar(Integer seminarId, Integer turningClassId) {
        return seminarDao.getSeminar(seminarId,turningClassId);
    }
}
