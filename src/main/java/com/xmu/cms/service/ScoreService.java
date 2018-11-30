package com.xmu.cms.service;

import com.xmu.cms.entity.Score;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
public interface ScoreService {
    Score getAllScoresInSeminar(Integer seminarId,Integer turningClassId);

    Score getAllScoresInRound(Integer roundId);
}
