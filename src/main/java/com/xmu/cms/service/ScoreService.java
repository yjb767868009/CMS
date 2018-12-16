package com.xmu.cms.service;

import com.xmu.cms.entity.SeminarScore;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
public interface ScoreService {
    SeminarScore getAllScoresInKlassSeminar(Integer klassSeminarId);

    SeminarScore getAllScoresInRound(Integer roundId);
}
