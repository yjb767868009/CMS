package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.KlassDao;
import com.xmu.cms.dao.StudentDao;
import com.xmu.cms.entity.Student;
import com.xmu.cms.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/5.
 * @version 1.0
 */
@Service
public class FileServiceImpl implements FileService {


    @Autowired
    private StudentDao studentDao;

    @Autowired
    private KlassDao klassDao;


    @Override
    public void uploadKlassFile(BigInteger klassId, List<Student> students) throws Exception {
        studentDao.newStudent(students);
        klassDao.addStudentInKlass(klassId, students);
    }

    @Override
    public void uploadAttendanceFile(BigInteger attendanceId, MultipartFile file) {
        //String filePath = UPLOADED_FOLDER + "attendance" + File.separator + attendanceId.toString() + File.separator + file.getOriginalFilename();
        //return saveFile(filePath, file);
    }
}
