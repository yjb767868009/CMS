package com.xmu.cms.controller;

import com.xmu.cms.aspect.CheckTeacherPermission;
import com.xmu.cms.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/api/attendance/{attendanceId}")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    @PatchMapping(value = "/setScore")
    @CheckTeacherPermission
    public Map<String,String> setAttendancePresentationScore(@PathVariable("attendanceId") Integer attendanceId,
                                                             @RequestParam("presentationScore") Integer presentationScore) {
        return attendanceService.setAttendancePresentationScore(attendanceId, presentationScore);
    }
}
