package com.xmu.cms.service;

import com.xmu.cms.entity.Seminar;

import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
public interface ClbumService {
    Map<String, String> newClbum(Integer courseId, String name, String classTime, String classPlace);

    List<Seminar> getStudentClbumNameByCourseId(Integer studentId, Integer courseId);
}
