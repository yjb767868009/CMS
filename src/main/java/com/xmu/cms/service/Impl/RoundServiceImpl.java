package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.RoundDao;
import com.xmu.cms.dao.SeminarDao;
import com.xmu.cms.entity.Round;
import com.xmu.cms.entity.Seminar;
import com.xmu.cms.service.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@Service
public class RoundServiceImpl implements RoundService {
    @Autowired
    private RoundDao roundDao;

    @Autowired
    private SeminarDao seminarDao;

    @Override
    public List<Round> getRoundsByCourseId(Integer courseId) {
        return roundDao.getRoundsByCourseId(courseId);
    }

    @Override
    public Seminar getAllSeminarInRound(Integer roundId) {
        return seminarDao.getAllSeminarByRoundId(roundId);
    }
}
