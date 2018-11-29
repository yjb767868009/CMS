package com.xmu.cms.service;

import com.xmu.cms.entity.SeminarInfo;

import java.util.List;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
public interface SeminarInfoService {
    List<SeminarInfo> getSeminarsByCourseId(Integer courseId);

    SeminarInfo getSeminarById(Integer seminarId);

    String newSeminar(Integer courseId, String topic, String introduction, Boolean visible);

    String modifySeminar(Integer courseId, Integer seminarId, String topic, String introduction, Boolean visible);
}
