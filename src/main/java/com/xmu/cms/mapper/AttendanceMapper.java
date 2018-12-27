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

    /**
     * 获取班级讨论课中的展示，按展示顺序排序
     *
     * @param seminarId 讨论课id
     * @param klassId   班级id
     * @return 展示列表
     */
    List<Attendance> getAttendancesInKlassAndSeminar(@Param("seminarId") BigInteger seminarId,
                                                     @Param("klassId") BigInteger klassId);

    /**
     * 删除展示信息
     *
     * @param attendanceId 展示id
     */
    void deleteAttendance(@Param("attendanceId") BigInteger attendanceId);

    /**
     * 查找学生在班级讨论课下的展示
     *
     * @param studentId 学生id
     * @param klassId   班级id
     * @param seminarId 讨论课id
     * @return 展示
     */
    Attendance getStudentAttendanceInKlassAndSeminar(@Param("studentId") BigInteger studentId,
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
    void updateAttendancePresent(@Param("attendance") Attendance attendance);

    /**
     * 根据自身id获取展示对象
     *
     * @param attendanceId 展示id
     * @return 展示
     */
    Attendance getAttendanceByAttendanceId(@Param("attendanceId") BigInteger attendanceId);

    /**
     * 展示上传报告
     *
     * @param attendanceId 展示id
     * @param filename     报告文件名
     */
    void attendanceUploadReport(@Param("attendanceId") BigInteger attendanceId,
                                @Param("fileName") String filename);

    /**
     * 展示上传PPT
     *
     * @param attendanceId 展示id
     * @param filename     PPT文件名
     */
    void attendanceUploadPPT(@Param("attendanceId") BigInteger attendanceId,
                             @Param("fileName") String filename);

    /**
     * 新建展示
     *
     * @param attendance 新的展示
     */
    void insertAttendance(@Param("attendance") Attendance attendance);

    /**
     * 获取学生在班级讨论课下的展示
     *
     * @param studentId      学生id
     * @param klassSeminarId 班级讨论课id
     * @return 展示
     */
    Attendance getStudentAttendanceInKlassSeminar(@Param("studentId") BigInteger studentId,
                                                  @Param("klassSeminarId") BigInteger klassSeminarId);

    /**
     * 获取队伍在轮次中的所有展示
     *
     * @param teamId  队伍id
     * @param roundId 轮次id
     * @return 展示列表
     */
    List<Attendance> getTeamAttendancesInRound(BigInteger teamId, BigInteger roundId);// TODO: 2018/12/27
}
