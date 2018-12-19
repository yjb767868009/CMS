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
    //todo

    /**
     * 获取轮次信息
     *
     * @param roundId 轮次ID
     * @return Round的信息
     */
    Round getRoundByRoundId(Integer roundId);//todo

    /**
     * 更新轮次成绩计算规则
     * @param round 修改的轮次信息
     * @return 修改轮次的信息
     */
    Integer updateCalType(Round round);
    //todo
}
