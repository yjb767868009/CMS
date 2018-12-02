package com.xmu.cms.dao;

import com.xmu.cms.entity.Score;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@Mapper
public interface ScoreDao {
    Score getAllScoresInSeminar(@Param("seminarId") Integer seminarId,
                                @Param("clbumId") Integer clbumId);

    Score getAllScoresInRound(@Param("roundId") Integer roundId);
}
