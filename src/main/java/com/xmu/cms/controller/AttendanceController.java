package com.xmu.cms.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping(value="")
public class AttendanceController {

    @PutMapping(value="/attendance/{attendanceId}")
    public Map<String,String> updateAttendance(@PathVariable("attendanceId") Integer attendanceId){
        //TODO
        return null;
    }

    @DeleteMapping(value="/attendance/{attendanceId}")
    public Map<String,String> cancelAttendance(@PathVariable("attendanceId") Integer attendanceId){
        //TODO
        return null;
    }

    @PostMapping(value="/attendance/{attendanceId}/report")
    public Map<String,String> uploadReport(@PathVariable("attendanceId") Integer attendanceId,
                                           @RequestParam("file") MultipartFile file){
        //TODO
        return null;
    }

    @PutMapping(value="/attendace/{attendanceId}/report")
    public Map<String,String> reuploadReport(@PathVariable("attendanceId") Integer attendanceId,
                                             @RequestParam("file") MultipartFile file){
        //TODO
        return null;
    }

    @GetMapping(value="/attendance/{attendanceId}/report")
    public void downloadReport(@PathVariable("attendanceId") Integer attendanceId){
        //TODO  下载讨论课报告
        return;
    }

    @PostMapping(value="/attendance/{attendanceId}/powerpoint")
    public Map<String,String> uploadPPT(@PathVariable("attendanceId") Integer attendanceId){
        //TODO 上传讨论课PPT
        return null;
    }

    @PutMapping(value="/attendance/{attendanceId}/powerpoint")
    public Map<String,String> reuploadPPT(@PathVariable("attendanceId") Integer attendanceId){
        //TODO 重传讨论课PPT
        return null;
    }

    @GetMapping(value="/attendance/{attendanceId}/powerpoint")
    public void downloadPPT(@PathVariable("attendanceId") Integer attendanceId){
        //TODO 下载讨论课PPT
        return;
    }
}
