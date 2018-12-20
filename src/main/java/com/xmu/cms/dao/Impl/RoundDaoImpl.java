package com.xmu.cms.dao.Impl;

import com.xmu.cms.dao.RoundDao;
import com.xmu.cms.entity.Round;
import com.xmu.cms.mapper.RoundMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/18.
 * @version 1.0
 */
@Component
public class RoundDaoImpl implements RoundDao {
    @Autowired
    private RoundMapper roundMapper;

    @Override
    public List<Round> getRoundsByCourseId(BigInteger courseId) {
        return roundMapper.getRoundsByCourseId(courseId);
    }

    @Override
    public Round getRoundById(BigInteger roundId) {
        return roundMapper.getRoundByRoundId(roundId);
    }

    @Override
    public Integer newRound(Round round) {
        return roundMapper.insertRound(round);
    }

    @Override
    public Integer updateCalType(Round round) {
        return roundMapper.updateCalType(round);
    }
}
