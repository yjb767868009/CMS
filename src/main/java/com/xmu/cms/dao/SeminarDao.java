package com.xmu.cms.dao;

import java.sql.Timestamp;

import com.xmu.cms.entity.Seminar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@Mapper
public interface SeminarDao {
    Integer newSeminar(@Param("roundId") Integer roundId,
                       @Param("maxTeamNum") Integer maxTeamNum,
                       @Param("topic") String topic,
                       @Param("introduction") String introduction,
                       @Param("signStartTime") Timestamp signStartTime,
                       @Param("signEndTime") Timestamp signEndTime,
                       @Param("signOrder") Boolean signOrder,
                       @Param("visible") Boolean visible);

    Integer modifySeminar(@Param("seminarId") Integer seminarId,
                          @Param("roundId") Integer roundId,
                          @Param("maxTeamNum") Integer maxTeamNum,
                          @Param("topic") String topic,
                          @Param("introduction") String introduction,
                          @Param("signStartTime") Timestamp signStartTime,
                          @Param("signEndTime") Timestamp signEndTime,
                          @Param("signOrder") Boolean signOrder,
                          @Param("visible") Boolean visible);


    Integer startClbumSeminar(@Param("seminarId") Integer seminarId,
                              @Param("clbumId") Integer clbumId);

    Integer stopClbumSeminar(@Param("seminarId") Integer seminarId,
                             @Param("clbumId") Integer clbumId);

    Integer endClbumSeminar(@Param("seminarId") Integer seminarId,
                            @Param("clbumId") Integer clbumId);

    Integer getAttendanceNo(@Param("seminarId") Integer seminarId);

    Seminar getSeminarBySeminarId(@Param("seminarId") Integer seminarId);

    Seminar getAllSeminarByRoundId(@Param("roundId") Integer roundId);


}
