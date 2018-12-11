package com.xmu.cms.mapper;

import java.sql.Timestamp;
import java.util.List;

import com.xmu.cms.entity.Seminar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@Mapper
public interface SeminarMapper {
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


    Integer startClbumSeminar(@Param("clbumSeminarId") Integer clbumSeminarId);

    Integer stopClbumSeminar(@Param("clbumSeminarId") Integer clbumSeminarId);

    Integer endClbumSeminar(@Param("clbumSeminarId") Integer clbumSeminarId);

    Integer getAttendanceNo(@Param("seminarId") Integer seminarId);

    Seminar getSeminarBySeminarId(@Param("seminarId") Integer seminarId);

    List<Seminar> getAllSeminarByRoundId(@Param("roundId") Integer roundId);

    Seminar getRunningSeminarByTeacherId(@Param("teacherId") Integer teacherId);
}
