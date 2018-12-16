package com.xmu.cms.service.Impl;

import com.xmu.cms.mapper.ScoreMapper;
import com.xmu.cms.entity.SeminarScore;
import com.xmu.cms.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreMapper scoreDao;

    @Override
    public SeminarScore getAllScoresInKlassSeminar(Integer klassSeminarId) {
        return scoreDao.getAllScoresInSeminar(klassSeminarId);
    }

    @Override
    public SeminarScore getAllScoresInRound(Integer roundId) {
        return scoreDao.getAllScoresInRound(roundId);
    }
}
