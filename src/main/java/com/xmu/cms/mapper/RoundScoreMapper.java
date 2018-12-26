package com.xmu.cms.mapper;

import com.xmu.cms.entity.RoundScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * @author BiqunZhou on 2018/12/5.
 * @version 2.0
 */
@Mapper
public interface RoundScoreMapper {

    /**
     * 获取所有队伍轮次成绩
     *
     * @param roundId 轮次Id
     * @return 轮次成绩列表
     */
    List<RoundScore> getAllScoresInRound(@Param("roundId") BigInteger roundId);

    /**
     * 获取队伍轮次的成绩
     *
     * @param roundId 轮次Id
     * @param teamId  队伍Id
     * @return 轮次成绩
     */
    RoundScore getTeamScoreInRound(@Param("roundId") BigInteger roundId,
                                   @Param("teamId") BigInteger teamId);
}
