package com.xmu.cms.service;

import com.xmu.cms.entity.Presentation;
import com.xmu.cms.entity.Seminar;

import java.util.Date;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
public interface SeminarService {
    String newSeminar(Integer seminarId, Integer turningClassId, Integer roundId, Integer maxTeamNum, Date signStartTime, Date signEndTime, Boolean signOrder);

    String modifySeminar(Integer seminarId, Integer turningClassId, Integer roundId, Integer maxTeamNum, Date signStartTime, Date signEndTime, Date reportEndTime, Integer status, Boolean signOrder);

    String startSeminar(Integer seminarId, Integer turningClassId);

    String stopSeminar(Integer seminarId, Integer turningClassId);

    String endSeminar(Integer seminarId, Integer turningClassId);

    Presentation getNextPresentation(Integer seminarId);

    Seminar getSeminar(Integer seminarId, Integer turningClassId);
}
