package com.xmu.cms.dao;

import com.xmu.cms.entity.Round;

import java.util.List;

/**
 * @author JuboYu on 2018/12/18.
 * @version 1.0
 */
public interface RoundDao {
    List<Round> getRoundsByCourseId(Integer courseId);

    Round getRoundById(Integer roundId);

    Integer newRound(Round round);
}
