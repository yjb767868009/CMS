package com.xmu.cms.mapper;

import com.xmu.cms.entity.Attendance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@Mapper
public interface AttendanceMapper {
    List<Attendance> getAttendancesInSeminar(@Param("seminarId") BigInteger seminarId);

    Integer setAttendanceScore(@Param("attendanceId") BigInteger attendanceId,
                               @Param("presentationScore") Float presentationScore);

    List<Attendance> getAttendancesInKlassSeminar(@Param("klassSeminarId") BigInteger klassSeminarId);
}
