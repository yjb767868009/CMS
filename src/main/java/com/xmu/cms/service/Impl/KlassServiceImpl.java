package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.KlassDao;
import com.xmu.cms.entity.Klass;
import com.xmu.cms.service.KlassService;
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
public class KlassServiceImpl implements KlassService {
    @Autowired
    private KlassDao klassDao;

    @Override
    public Map<String, String> newKlass(Integer courseId, Klass klass) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = klassDao.newKlass(courseId, klass);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
    }

    @Override
    public List<Klass> getKlassInCourse(Integer courseId) {
        return klassDao.getAllKlass(courseId);
    }
}
