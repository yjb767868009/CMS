package com.xmu.cms.controller;

import com.xmu.cms.entity.Attendance;
import com.xmu.cms.entity.SeminarScore;
import com.xmu.cms.service.SeminarService;
import com.xmu.cms.support.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/25.
 * @version 1.0
 */

@RestController
@RequestMapping(value = "")
public class AttendanceController {

    @Autowired
    private SeminarService seminarService;

    @PutMapping(value = "/attendance/{attendanceId}")
    public Map<String, String> updateAttendance(@PathVariable("attendanceId") BigInteger attendanceId,
                                                @RequestBody Attendance attendance) {
        //TODO
        Map<String, String> message = new HashMap<String, String>(1);
        try {
            attendance.setAttendanceId(attendanceId);
            message.put("message", "Success");
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }
        return message;
    }

    @DeleteMapping(value = "/attendance/{attendanceId}")
    public Map<String, String> cancelAttendance(@PathVariable("attendanceId") BigInteger attendanceId) {
        Map<String, String> message = new HashMap<String, String>(1);
        try {
            seminarService.deleteAttendance(attendanceId);
            message.put("message", "Success");
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }
        return message;
    }

    @Secured("ROLE_STUDENT")
    @PutMapping(value = "/attendance/{attendanceId}/report")
    public Map<String, String> uploadReport(@PathVariable("attendanceId") BigInteger attendanceId,
                                            @RequestParam("file") MultipartFile file) {
        Map<String, String> message = new HashMap<String, String>(1);
        try {
            FileUtils.uploadAttendanceFile(attendanceId, file);
            seminarService.attendanceUploadReport(attendanceId, file.getOriginalFilename());
            message.put("message", "Success");
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }
        return message;
    }

    @Secured("ROLE_STUDENT")
    @PutMapping(value = "/attendance/{attendanceId}/powerpoint")
    public Map<String, String> uploadPPT(@PathVariable("attendanceId") BigInteger attendanceId,
                                         @RequestParam("file") MultipartFile file) {
        Map<String, String> message = new HashMap<String, String>(1);
        try {
            FileUtils.uploadAttendanceFile(attendanceId, file);
            seminarService.attendanceUploadPPT(attendanceId, file.getOriginalFilename());
            message.put("message", "Success");
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }
        return message;
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/attendance/{attendanceId}/report")
    public Map<String, String> downloadReport(@PathVariable("attendanceId") BigInteger attendanceId) {
        Map<String, String> message = new HashMap<String, String>(1);
        try {
            Attendance attendance = seminarService.getAttendanceByAttendanceId(attendanceId);
            FileUtils.downloadAttendanceFile(attendanceId, attendance.getReportFile());
            message.put("message", "Success");
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }
        return message;
    }

    @Secured({"ROLE_TEACHER", "ROLE_STUDENT"})
    @GetMapping(value = "/attendance/{attendanceId}/powerpoint")
    public Map<String, String> downloadPPT(@PathVariable("attendanceId") BigInteger attendanceId, @RequestBody Attendance attendance) throws UnsupportedEncodingException {
        Map<String, String> message = new HashMap<String, String>(1);
        try {
            FileUtils.downloadAttendanceFile(attendanceId, attendance.getPresentationFile());
            message.put("message", "Success");
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }
        return message;
    }

    @Secured("ROLE_TEACHER")
    @PutMapping(value = "/attendance/{attendanceId}/reportscore")
    public Map<String, String> scoreAttendanceReportScore(@PathVariable("attendanceId") BigInteger attendanceId,
                                                          @RequestBody SeminarScore seminarScore) {
        Map<String, String> message = new HashMap<String, String>(1);
        try {
            seminarService.scoreReportScore(attendanceId, seminarScore);
            message.put("message", "Success");
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }
        return message;
    }

    @Secured("ROLE_TEACHER")
    @PutMapping(value = "/attendance/{attendanceId}/presentationscore")
    public Map<String, String> scoreAttendancePresentationScore(@PathVariable("attendanceId") BigInteger attendanceId,
                                                                @RequestBody SeminarScore seminarScore) {
        Map<String, String> message = new HashMap<String, String>(1);
        try {
            seminarService.scorePresentationScore(attendanceId, seminarScore);
            message.put("message", "Success");
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }
        return message;
    }
}
