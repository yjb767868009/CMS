package com.xmu.cms.service;

import com.xmu.cms.entity.Round;
import com.xmu.cms.entity.Seminar;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
public interface RoundService {
    List<Round> getRoundsByCourseId(Integer courseId);

    Seminar getAllSeminarInRound(Integer roundId);

    }
