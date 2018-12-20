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

    List<Attendance> getAttendancesInKlassSeminar(@Param("klassSeminarId") Integer klassSeminarId);

    Map<String, String> studentAttendance(@Param("teamId") Integer teamId,
                                          @Param("klassSeminarId") Integer klassSeminarId,
                                          @Param("teamOrder") Integer teamOrder);

    Integer newAttendance(@Param("klassSeminarId") Integer klassSeminarId,
                          @Param("teamId") Integer teamId,
                          @Param("teamOrder") Integer teamOrder,
                          @Param("present") Integer present,
                          @Param("reportName") String reportName,
                          @Param("reportUrl") String reportUrl,
                          @Param("pptName") String pptName,
                          @Param("pptUrl") String pptUrl);

    Integer deleteAttendance(@Param("attendanceId") Integer attendanceId);
}
