package com.xmu.cms.service.Impl;

import com.xmu.cms.mapper.ScoreMapper;
import com.xmu.cms.entity.Score;
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
    public Score getAllScoresInClbumSeminar(Integer clbumSeminarId) {
        return scoreDao.getAllScoresInSeminar(clbumSeminarId);
    }

    @Override
    public Score getAllScoresInRound(Integer roundId) {
        return scoreDao.getAllScoresInRound(roundId);
    }
}
