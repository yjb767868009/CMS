package com.xmu.cms.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author JuboYu on 2018/12/5.
 * @version 1.0
 */
public interface FileService {

    Map<String, String> uploadKlassFile(Integer klassId, MultipartFile file);

    Map<String, String> uploadAttendanceFile(Integer attendanceId, MultipartFile file);
}
