package com.xmu.cms.service.Impl;

import com.xmu.cms.mapper.RoundMapper;
import com.xmu.cms.mapper.SeminarMapper;
import com.xmu.cms.entity.Round;
import com.xmu.cms.entity.Seminar;
import com.xmu.cms.service.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@Service
public class RoundServiceImpl implements RoundService {
    @Autowired
    private RoundMapper roundMapper;

    @Autowired
    private SeminarMapper seminarMapper;

    @Override
    public List<Round> getRoundsByCourseId(Integer courseId) {
        return roundMapper.getRoundsByCourseId(courseId);
    }

    @Override
    public List<Seminar> getAllSeminarInRound(Integer roundId) {
        return seminarMapper.getAllSeminarByRoundId(roundId);
    }

    @Override
    public Map<String, String> newRound(Integer courseId, Integer roundNum, Integer presentationScoreType, Integer reportScoreType, Integer questionScoreType) {
        return roundMapper.newRound(courseId, roundNum, presentationScoreType, reportScoreType, questionScoreType);
    }
}
