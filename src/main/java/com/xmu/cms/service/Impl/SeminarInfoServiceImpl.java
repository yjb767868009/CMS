package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.SeminarInfoDao;
import com.xmu.cms.entity.SeminarInfo;
import com.xmu.cms.service.SeminarInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
@Service
public class SeminarInfoServiceImpl implements SeminarInfoService {
    @Autowired
    private SeminarInfoDao seminarInfoDao;

    @Override
    public List<SeminarInfo> getSeminarsByCourseId(Integer courseId) {
        return seminarInfoDao.getAllSeminarByCourseId(courseId);
    }

    @Override
    public SeminarInfo getSeminarById(Integer seminarId) {
        return seminarInfoDao.getSeminarById(seminarId);
    }

    @Override
    public String newSeminar(Integer courseId, String topic, String introduction, Boolean visible) {
        return seminarInfoDao.newSeminar(courseId, topic, introduction, visible);
    }

    @Override
    public String modifySeminar(Integer courseId, Integer seminarId, String topic, String introduction, Boolean visible) {
        return seminarInfoDao.modifySeminar(courseId,seminarId, topic, introduction, visible);
    }
}
