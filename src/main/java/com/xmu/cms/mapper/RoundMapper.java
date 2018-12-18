package com.xmu.cms.mapper;

import com.xmu.cms.entity.Round;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@Mapper
public interface RoundMapper {
    List<Round> getRoundsByCourseId(@Param("courseId") Integer courseId);

    /**
     * 新建轮次
     * @param round Round信息
     * @return 新建round成功信息
     */
    Integer insertRound(Round round);

    /**
     * 获取轮次信息
     *
     * @param roundId 轮次ID
     * @return Round的信息
     */
    Round getRoundByRoundId(Integer roundId);
}
