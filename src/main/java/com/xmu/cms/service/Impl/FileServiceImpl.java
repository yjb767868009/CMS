package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.KlassDao;
import com.xmu.cms.dao.StudentDao;
import com.xmu.cms.entity.Student;
import com.xmu.cms.service.FileService;
import com.xmu.cms.support.XLSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/12/5.
 * @version 1.0
 */
@Service
public class FileServiceImpl implements FileService {

    @Value("${file.folder}")
    private String UPLOADED_FOLDER;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private KlassDao klassDao;

    private Map<String, String> saveFile(String filePath, MultipartFile file) {
        Map<String, String> messages = new HashMap<String, String>(2);
        if (file.isEmpty()) {
            messages.put("message", "Please select a file");
            return messages;
        }
        String fatherPath = filePath.substring(0, filePath.lastIndexOf("\\"));
        File fatherFile = new File(fatherPath);
        if (!fatherFile.exists()) {
            fatherFile.mkdirs();
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(filePath);
            Files.write(path, bytes);
            messages.put("message", "Success");
        } catch (IOException e) {
            messages.put("message", "Error");
            return messages;
        }
        return messages;
    }

    @Override
    public Map<String, String> uploadKlassFile(Integer klassId, MultipartFile file) {
        String filePath = UPLOADED_FOLDER + "class" + File.separator + klassId.toString() + File.separator + file.getOriginalFilename();
        Map<String, String> messages = new HashMap<String, String>(2);

        messages = saveFile(filePath, file);
        try {
            List<List<Object>> studentList = XLSUtils.analysisFile(filePath);
            List<Student> students = new ArrayList<Student>();
            for (List<Object> studentMessage : studentList) {
                String account = (String) studentMessage.get(0);
                String name = (String) studentMessage.get(1);
                Student student = new Student();
                student.setAccount(account);
                student.setName(name);
                student.setEmail("");
                student.setPassword("123456");
                student.setActivation(false);
                students.add(student);
            }
            studentDao.newStudent(students);
            klassDao.addStudentInKlass(klassId, students);
        } catch (Exception e) {
            messages.put("message", e.getMessage());
        }
        return messages;
    }

    @Override
    public Map<String, String> uploadAttendanceFile(Integer attendanceId, MultipartFile file) {
        String filePath = UPLOADED_FOLDER + "attendance" + File.separator + attendanceId.toString() + File.separator + file.getOriginalFilename();
        return saveFile(filePath, file);
    }
}
