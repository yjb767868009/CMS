package com.xmu.cms.mapper;

import com.xmu.cms.entity.SeminarScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * @author BiqunZhou on 2018/12/5.
 * @version 2.0
 */
@Mapper
public interface SeminarScoreMapper {
    /**
     * 获取讨论课中分数信息
     *
     * @param klassSeminarId 班级讨论课id
     * @return 讨论课分数
     */
    SeminarScore getAllScoresInSeminar(@Param("clbumSeminarId") BigInteger klassSeminarId);

    /**
     * 删除讨论课成绩
     *
     * @param seminarId 讨论课id
     * @return 删除信息
     */
    Integer deleteSeminarScore(@Param("seminarId") BigInteger seminarId);

    /**
     * 获取轮次中队伍的所有讨论课成绩
     *
     * @param teamId  队伍id
     * @param roundId 轮次id
     * @return 讨论课成绩列表
     */
    List<SeminarScore> getTeamSeminarScoreInRound(@Param("teamId") BigInteger teamId, @Param("roundId") BigInteger roundId);
}
