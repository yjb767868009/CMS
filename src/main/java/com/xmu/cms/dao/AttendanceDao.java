package com.xmu.cms.dao;

import com.xmu.cms.entity.Attendance;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/19.
 * @version 1.0
 */
public interface AttendanceDao {
    /**
     * 根据讨论课和班级获取所有的展示
     *
     * @param seminarId 讨论课id
     * @param klassId   班级id
     * @return 讨论课列表
     */
    List<Attendance> getAttendancesInKlassAndSeminar(BigInteger seminarId, BigInteger klassId);

    /**
     * 获取学生在班级和讨论课下的展示
     *
     * @param studentId 学生id
     * @param klassId   班级id
     * @param seminarId 展示id
     * @return 展示
     */
    Attendance getStudentAttendanceInKlassSeminar(BigInteger studentId, BigInteger klassId, BigInteger seminarId);

    /**
     * 获取在班级讨论课下的所有展示
     *
     * @param klassSeminarId 班级讨论课id
     * @return 班级讨论课列表
     */
    List<Attendance> getAttendancesInKlassSeminar(BigInteger klassSeminarId);

    /**
     * 修改展示的进行状态
     *
     * @param attendance 新的展示
     */
    void updateAttendancePresent(Attendance attendance);

    /**
     * 根据id获取对应展示
     *
     * @param attendanceId 展示id
     * @return 展示
     */
    Attendance getAttendanceByAttendanceId(BigInteger attendanceId);

    /**
     * 修改展示上传的报告名
     *
     * @param attendanceId 展示id
     * @param filename     报告文件名
     */
    void attendanceUploadReport(BigInteger attendanceId, String filename);

    /**
     * 修改展示的ppt名
     *
     * @param attendanceId 展示id
     * @param filename     ppt文件名
     */
    void attendanceUploadPPT(BigInteger attendanceId, String filename);

    /**
     * 根据id删除展示
     *
     * @param attendanceId 展示id
     */
    void deleteAttendance(BigInteger attendanceId);

    /**
     * 新建展示
     *
     * @param attendance 展示
     */
    void insertAttendance(Attendance attendance);
}
