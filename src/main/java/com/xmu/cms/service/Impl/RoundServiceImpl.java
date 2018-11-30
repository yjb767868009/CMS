package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.RoundDao;
import com.xmu.cms.entity.Round;
import com.xmu.cms.service.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@Service
public class RoundServiceImpl implements RoundService {
    @Autowired
    private RoundDao roundDao;

    @Override
    public List<Round> getRoundsByCourseId(Integer courseId) {
        return roundDao.getRoundsByCourseId(courseId);
    }
}
