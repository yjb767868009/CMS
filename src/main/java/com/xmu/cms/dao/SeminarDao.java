package com.xmu.cms.dao;

import java.util.Date;

import com.xmu.cms.entity.Seminar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@Mapper
public interface SeminarDao {
    Integer newSeminar(@Param("seminarId") Integer seminarId,
                       @Param("turningClassId") Integer turningClassId,
                       @Param("roundId") Integer roundId,
                       @Param("maxTeamNum") Integer maxTeamNum,
                       @Param("signStartTime") Date signStartTime,
                       @Param("signEndTime") Date signEndTime,
                       @Param("signOrder") Boolean signOrder);

    Integer modifySeminar(@Param("seminarId") Integer seminarId,
                          @Param("turningClassId") Integer turningClassId,
                          @Param("roundId") Integer roundId,
                          @Param("maxTeamNum") Integer maxTeamNum,
                          @Param("signStartTime") Date signStartTime,
                          @Param("signEndTime") Date signEndTime,
                          @Param("signOrder") Boolean signOrder);

    Integer startSeminar(@Param("seminarId") Integer seminarId,
                         @Param("turningClassId") Integer turningClassId);

    Integer stopSeminar(@Param("seminarId") Integer seminarId,
                        @Param("turningClassId") Integer turningClassId);

    Integer endSeminar(@Param("seminarId") Integer seminarId,
                       @Param("turningClassId") Integer turningClassId);

    Integer getPresentationNo(@Param("seminarId") Integer seminarId);

    Seminar getSeminar(@Param("seminarId") Integer seminarId,
                       @Param("turningClassId") Integer turningClassId);
}
