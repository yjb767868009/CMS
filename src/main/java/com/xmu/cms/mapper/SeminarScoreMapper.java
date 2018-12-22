package com.xmu.cms.mapper;

import com.xmu.cms.entity.SeminarScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;

/**
 * @author BiqunZhou on 2018/12/5.
 * @version 2.0
 */
@Mapper
public interface SeminarScoreMapper {
    SeminarScore getAllScoresInSeminar(@Param("clbumSeminarId") BigInteger clbumSeminarId);

    SeminarScore getAllScoresInRound(@Param("roundId") BigInteger roundId);

    /**
     * 删除讨论课成绩
     *
     * @param seminarId 讨论课id
     * @return 删除信息
     */
    Integer deleteSeminarScore(BigInteger seminarId);
}
