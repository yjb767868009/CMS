package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.TurningClassDao;
import com.xmu.cms.entity.TurningClass;
import com.xmu.cms.service.TurningClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@Service
public class TurningClassServiceImpl implements TurningClassService {
    @Autowired
    private TurningClassDao turningClassDao;

    @Override
    public String newTurningClass(Integer courseId, String name, String classTime, String classPlace) {
        Integer count=turningClassDao.newTurningClass(courseId,name,classTime,classPlace);
        if (count==0){
            return "Create Error";
        }else {
            return "Success";
        }
    }
}
