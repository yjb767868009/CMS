package com.xmu.cms.service;

import com.xmu.cms.entity.Seminar;

import java.util.List;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
public interface SeminarService {
    List<Seminar> getSeminarsByCourseId(Integer courseId);

    Seminar getSeminarById(Integer seminarId);

    String newSeminar(Integer courseId, String topic, String introduction, Boolean visible);

    String modifySeminar(Integer courseId, Integer seminarId, String topic, String introduction, Boolean visible);
}
