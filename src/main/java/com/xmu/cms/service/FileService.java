package com.xmu.cms.service;

import com.xmu.cms.entity.Student;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/5.
 * @version 1.0
 */
public interface FileService {

    void uploadKlassFile(BigInteger klassId, List<Student> students) throws Exception;

    void uploadAttendanceFile(BigInteger attendanceId, MultipartFile file);
}
