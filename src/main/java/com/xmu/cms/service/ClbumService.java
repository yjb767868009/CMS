package com.xmu.cms.service;

import com.xmu.cms.entity.Seminar;

import java.util.List;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
public interface ClbumService {
    String newClbum(Integer courseId, String name, String classTime, String classPlace);

    List<Seminar> getStudentClbumNameByCourseId(Integer studentId, Integer courseId);
}
