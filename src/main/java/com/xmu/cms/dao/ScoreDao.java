package com.xmu.cms.dao;

import com.xmu.cms.entity.Score;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author BiqunZhou on 2018/12/5.
 * @version 2.0
 */
@Mapper
public interface ScoreDao {
    Score getAllScoresInSeminar(@Param("seminarId") Integer seminarId,
                                @Param("clbumId") Integer clbumId);

    Score getAllScoresInRound(@Param("roundId") Integer roundId);

    Integer modifyScoreInRound(@Param("roundId") Integer roundId,
                               @Param("presentationScore") Integer presentationScore,
                               @Param("reportScore") Integer reportScore,
                               @Param("questionScore") Integer questionScore);

    Integer modifyScoreInSeminar(@Param("seminarId") Integer seminarId,
                               @Param("presentationScore") Integer presentationScore,
                               @Param("reportScore") Integer reportScore,
                               @Param("questionScore") Integer questionScore);

    Integer newScoreInRound(@Param("teamId") Integer teamId,
                            @Param("turningClassId") Integer turningClassId,
                            @Param("roundId") Integer roundId,
                            @Param("presentationScore") Integer presentationScore,
                            @Param("reportScore") Integer reportScore,
                            @Param("questionScore") Integer questionScore,
                            @Param("totalScore") Integer totalScore);

    Integer newScoreInSeminar(@Param("teamId") Integer teamId,
                            @Param("turningClassId") Integer turningClassId,
                            @Param("seminarId") Integer semianrId,
                            @Param("presentationScore") Integer presentationScore,
                            @Param("reportScore") Integer reportScore,
                            @Param("questionScore") Integer questionScore,
                            @Param("totalScore") Integer totalScore);
}
