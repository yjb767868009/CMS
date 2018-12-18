package com.xmu.cms.controller;

import com.xmu.cms.service.FileService;
import com.xmu.cms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author JuboYu on 2018/12/17.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/class")
public class KlassController {

    @Autowired
    private FileService fileService;

    @Autowired
    private CourseService courseService;

    @Secured("ROLE_TEACHER")
    @PutMapping(value = "/{classId}")
    public Map<String, String> uploadClassFile(@PathVariable("classId") Integer classId,
                                               @RequestParam("file") MultipartFile file) {
        return fileService.uploadKlassFile(classId, file);
    }

    @Secured("ROLE_TEACHER")
    @DeleteMapping(value = "/{classId}")
    public Map<String, String> deleteKlass(@PathVariable("classId") Integer classId) {
        return courseService.deleteKlass(classId);
    }
}
