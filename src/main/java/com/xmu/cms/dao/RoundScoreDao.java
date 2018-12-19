package com.xmu.cms.dao;

import com.xmu.cms.entity.RoundScore;

import java.util.List;

/**
 * @author JuboYu on 2018/12/18.
 * @version 1.0
 */
public interface RoundScoreDao {
    RoundScore getRoundTeamScore(Integer roundId, Integer teamId);

    List<RoundScore> getRoundScore(Integer roundId);
}
