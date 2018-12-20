package com.xmu.cms.dao.Impl;

import com.xmu.cms.dao.RoundScoreDao;
import com.xmu.cms.entity.RoundScore;
import com.xmu.cms.mapper.RoundScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/18.
 * @version 1.0
 */
@Component
public class RoundScoreDaoImpl implements RoundScoreDao {

    @Autowired
    private RoundScoreMapper roundScoreMapper;

    @Override
    public RoundScore getRoundTeamScore(BigInteger roundId, BigInteger teamId) {
        return roundScoreMapper.getScoreInRound(roundId, teamId);
    }

    @Override
    public List<RoundScore> getRoundScore(BigInteger roundId) {
        return roundScoreMapper.getAllScoresInRound(roundId);
    }
}
