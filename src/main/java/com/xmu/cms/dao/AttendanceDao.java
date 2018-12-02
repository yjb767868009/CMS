package com.xmu.cms.dao;

import com.xmu.cms.entity.Attendance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@Mapper
public interface AttendanceDao {
    List<Attendance> getAttendancesInSeminar(@Param("seminarId") Integer seminarId);

    Integer setAttendanceScore(@Param("attendanceId") Integer attendanceId,
                               @Param("presentationScore") Integer presentationScore);
}
