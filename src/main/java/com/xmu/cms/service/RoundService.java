package com.xmu.cms.service;

import com.xmu.cms.entity.Round;
import com.xmu.cms.entity.Seminar;

import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
public interface RoundService {
    List<Round> getRoundsByCourseId(Integer courseId);

    List<Seminar> getAllSeminarInRound(Integer roundId);

    Map<String, String> newRound(Integer courseId, Integer roundNum, Integer presentationScoreType, Integer reportScoreType, Integer questionScoreType);
}
