package com.xmu.cms.dao;

import com.xmu.cms.entity.RoundScore;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/18.
 * @version 1.0
 */
public interface RoundScoreDao {
    RoundScore getRoundTeamScore(BigInteger roundId, BigInteger teamId);

    List<RoundScore> getRoundScore(BigInteger roundId);

    void updateRoundScore(BigInteger roundId, BigInteger teamId);

}
