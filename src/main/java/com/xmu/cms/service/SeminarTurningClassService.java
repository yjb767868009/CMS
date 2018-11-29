package com.xmu.cms.service;

import java.util.Date;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
public interface SeminarTurningClassService {
    String newSeminarTurningClass(Integer seminarId, Integer turningClassId, Integer roundId, Integer maxTeamNum, Date signStartTime, Date signEndTime, Boolean signOrder);

    String modifySeminarTurningClass(Integer seminarId, Integer turningClassId, Integer roundId, Integer maxTeamNum, Date signStartTime, Date signEndTime, Date reportEndTime, Integer status, Boolean signOrder);

    String startSeminarTurningClass(Integer seminarId, Integer turningClassId);

    String stopSeminarTurningClass(Integer seminarId, Integer turningClassId);

    String endSeminarTurningClass(Integer seminarId, Integer turningClassId);

    String continueSeminarTurningClass(Integer seminarId, Integer turningClassId);
}
