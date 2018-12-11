package com.xmu.cms.mapper;

import com.xmu.cms.entity.Attendance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@Mapper
public interface AttendanceMapper {
    List<Attendance> getAttendancesInSeminar(@Param("seminarId") Integer seminarId);

    Integer setAttendanceScore(@Param("attendanceId") Integer attendanceId,
                               @Param("presentationScore") Integer presentationScore);

    List<Attendance> getAttendancesInClbumSeminar(@Param("clbumSeminarId") Integer clbumSeminarId);

    Map<String, String> studentAttendance(Integer teamId, Integer clbumSeminarId, Integer teamOrder);
}
