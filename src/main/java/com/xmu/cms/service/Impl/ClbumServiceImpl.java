package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.ClbumDao;
import com.xmu.cms.entity.Clbum;
import com.xmu.cms.entity.Seminar;
import com.xmu.cms.service.ClbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@Service
public class ClbumServiceImpl implements ClbumService {
    @Autowired
    private ClbumDao clbumDao;

    @Override
    public String newClbum(Integer courseId, String name, String classTime, String classPlace) {
        Integer count = clbumDao.newClbum(courseId, name, classTime, classPlace);
        if (count == 1) {
            return "Success";
        } else {
            return "Create Error";
        }
    }

    @Override
    public List<Seminar> getStudentClbumNameByCourseId(Integer studentId, Integer courseId) {
        return clbumDao.getStudentClbumByCourseId(studentId, courseId);
    }
}
