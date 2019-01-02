package com.xmu.cms.controller;

import com.xmu.cms.entity.Student;
import com.xmu.cms.service.CourseService;
import com.xmu.cms.support.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/12/17.
 * @version 1.0
 */
@RestController
public class KlassController {

    @Autowired
    private CourseService courseService;

    @Secured("ROLE_TEACHER")
    @PostMapping(value = "/class/{classId}/classfile")
    public Map<String, String> uploadClassFile(@PathVariable("classId") BigInteger klassId,
                                               @RequestParam("file") MultipartFile file) {
        Map<String, String> message = new HashMap<String, String>(1);
        try {
            List<Student> students = FileUtils.uploadKlassFile(klassId, file);
            courseService.uploadKlassFile(klassId, students);
            message.put("message", "Success");
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }
        return message;
    }

    @Secured("ROLE_TEACHER")
    @DeleteMapping(value = "/class/{classId}")
    public Map<String, String> deleteKlass(@PathVariable("classId") BigInteger classId) {
        Map<String, String> message = new HashMap<String, String>(1);
        try {
            courseService.deleteKlass(classId);
            message.put("message", "Success");
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }
        return message;
    }


}
