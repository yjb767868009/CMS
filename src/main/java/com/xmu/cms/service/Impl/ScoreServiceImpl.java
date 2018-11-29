package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.ScoreDao;
import com.xmu.cms.entity.Score;
import com.xmu.cms.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreDao scoreDao;

    @Override
    public Score getAllScoresInSeminarTurningClass(Integer seminarId, Integer turningClassId) {
        return scoreDao.getAllScoresInSeminarTurningClass(seminarId, turningClassId);
    }

    @Override
    public Score getAllScoresInRound(Integer roundId) {
        return scoreDao.getAllScoresInRound(roundId);
    }
}
