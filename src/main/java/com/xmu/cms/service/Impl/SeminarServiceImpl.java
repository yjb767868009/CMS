package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.SeminarDao;
import com.xmu.cms.entity.Seminar;
import com.xmu.cms.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
@Service
public class SeminarServiceImpl implements SeminarService {
    @Autowired
    private SeminarDao seminarDao;

    @Override
    public List<Seminar> getSeminarsByCourseId(Integer courseId) {
        return seminarDao.getAllSeminarByCourseId(courseId);
    }
}
