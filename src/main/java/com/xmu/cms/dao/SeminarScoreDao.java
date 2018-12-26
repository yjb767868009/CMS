package com.xmu.cms.dao;

import com.xmu.cms.entity.SeminarScore;
import com.xmu.cms.mapper.RoundScoreMapper;
import com.xmu.cms.mapper.SeminarScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/19.
 * @version 1.0
 */
@Component
public class SeminarScoreDao {
    @Autowired
    private SeminarScoreMapper seminarScoreMapper;

    @Autowired
    private RoundScoreMapper roundScoreMapper;

    public List<SeminarScore> getTeamSeminarScoreInRound(BigInteger teamId, BigInteger roundId) {
        return seminarScoreMapper.getTeamSeminarScoreInRound(teamId, roundId);
    }

    public void modifyTeamSeminarScore(SeminarScore seminarScore) {
        seminarScoreMapper.modifyTeamSeminarScore(seminarScore);
    }

    public void updateReportScore(SeminarScore seminarScore) {
        seminarScoreMapper.updateReportScore(seminarScore);
    }

    public void updatePresentationScore(SeminarScore seminarScore) {
        seminarScoreMapper.updatePresentationScore(seminarScore);
    }
}
