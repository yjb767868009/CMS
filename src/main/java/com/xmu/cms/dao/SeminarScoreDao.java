package com.xmu.cms.dao;

import com.xmu.cms.entity.SeminarScore;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/19.
 * @version 1.0
 */
public interface SeminarScoreDao {
    List<SeminarScore> getTeamSeminarScoreInRound(BigInteger teamId, BigInteger roundId);

    void modifyTeamSeminarScore(SeminarScore seminarScore);
}
