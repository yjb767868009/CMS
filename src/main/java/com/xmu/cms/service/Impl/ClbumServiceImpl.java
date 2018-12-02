package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.ClbumDao;
import com.xmu.cms.entity.Seminar;
import com.xmu.cms.service.ClbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@Service
public class ClbumServiceImpl implements ClbumService {
    @Autowired
    private ClbumDao clbumDao;

    @Override
    public Map<String, String> newClbum(Integer courseId, String name, String classTime, String classPlace) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = clbumDao.newClbum(courseId, name, classTime, classPlace);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
    }

    @Override
    public List<Seminar> getStudentClbumNameByCourseId(Integer studentId, Integer courseId) {
        return clbumDao.getStudentClbumByCourseId(studentId, courseId);
    }
}
