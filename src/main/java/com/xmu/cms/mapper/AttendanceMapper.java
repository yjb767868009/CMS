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
    /**
     * 获取讨论课中的展示
     *
     * @param seminarId 讨论课id
     * @return 展示列表
     */
    List<Attendance> getAttendancesInSeminar(@Param("seminarId") BigInteger seminarId);

    Integer setAttendanceScore(@Param("attendanceId") BigInteger attendanceId,
                               @Param("presentationScore") Float presentationScore);//没用了

    /**
     * 获取班级讨论课中的展示，按展示顺序排序
     *
     * @param seminarId 讨论课id
     * @param klassId   班级id
     * @return 展示列表
     */
    List<Attendance> getAttendancesInKlassAndSeminar(@Param("seminarId") BigInteger seminarId, @Param("klassId") BigInteger klassId);

    /**
     * 插入展示信息
     *
     * @param attendance 展示
     * @return 插入成功信息
     */
    Integer newAttendance(@Param("attendance") Attendance attendance);

    /**
     * 删除展示信息
     *
     * @param attendanceId 展示id
     * @return 删除成功信息
     */
    Integer deleteAttendance(@Param("attendanceId") BigInteger attendanceId);

    /**
     * 查找学生在班级讨论课下的展示
     *
     * @param studentId 学生id
     * @param klassId   班级id
     * @param seminarId 讨论课id
     * @return 展示
     */
    Attendance getStudentAttendanceInKlassSeminar(@Param("studentId") BigInteger studentId,
                                                  @Param("klassId") BigInteger klassId,
                                                  @Param("seminarId") BigInteger seminarId);

    /**
     * 获取班级讨论课中的展示，按展示顺序排序
     *
     * @param klassSeminarId 班级讨论课id
     * @return 展示列表
     */
    List<Attendance> getAttendancesInKlassSeminar(@Param("klassSeminarId") BigInteger klassSeminarId);

    /**
     * 修改展示状态
     *
     * @param attendance 展示
     */
    void updateAttendancePresent(Attendance attendance);
}
