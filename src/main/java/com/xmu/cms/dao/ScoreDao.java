package com.xmu.cms.dao;

import com.xmu.cms.entity.Score;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
public interface ScoreDao {
    Score getAllScoresInSeminarTurningClass(Integer seminarId, Integer turningClassId);

    Score getAllScoresInRound(Integer roundId);
}
