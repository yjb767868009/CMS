package com.xmu.cms.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
public interface SeminarTurningClassDao {
    Integer newSeminarTurningClass(@Param("seminarId") Integer seminarId,
                                   @Param("turningClassId") Integer turningClassId,
                                   @Param("roundId") Integer roundId,
                                   @Param("maxTeamNum") Integer maxTeamNum,
                                   @Param("signStartTime") Date signStartTime,
                                   @Param("signEndTime") Date signEndTime,
                                   @Param("signOrder") Boolean signOrder);

    Integer modifySeminarTurningClass(@Param("seminarId") Integer seminarId,
                                      @Param("turningClassId") Integer turningClassId,
                                      @Param("roundId") Integer roundId,
                                      @Param("maxTeamNum") Integer maxTeamNum,
                                      @Param("signStartTime") Date signStartTime,
                                      @Param("signEndTime") Date signEndTime,
                                      @Param("signOrder") Boolean signOrder);

    Integer startSeminarTurningClass(@Param("seminarId") Integer seminarId,
                                     @Param("turningClassId") Integer turningClassId);

    Integer stopSeminarTurningClass(@Param("seminarId") Integer seminarId,
                                    @Param("turningClassId") Integer turningClassId);

    Integer endSeminarTurningClass(@Param("seminarId") Integer seminarId,
                                   @Param("turningClassId") Integer turningClassId);
}
