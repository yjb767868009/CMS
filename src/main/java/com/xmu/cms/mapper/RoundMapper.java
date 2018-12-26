package com.xmu.cms.mapper;

import com.xmu.cms.entity.Round;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@Mapper
public interface RoundMapper {
    /**
     * 获取课程中的轮次信息
     *
     * @param courseId 课程id
     * @return 轮次列表
     */
    List<Round> getRoundsByCourseId(@Param("courseId") BigInteger courseId);

    /**
     * 新建轮次
     *
     * @param round Round信息
     * @return 新建round成功信息
     */
    Integer insertRound(@Param("round") Round round);


    /**
     * 获取轮次信息
     *
     * @param roundId 轮次ID
     * @return Round的信息
     */
    Round getRoundByRoundId(@Param("roundId") BigInteger roundId);

    /**
     * 更新轮次成绩计算规则
     *
     * @param round 修改的轮次信息
     * @return 修改轮次的信息
     */
    Integer updateCalType(@Param("round") Round round);

    /**
     * 删除轮次
     *
     * @param roundId 轮次id
     * @return 删除信息
     */
    Integer deleteRoundByRoundId(@Param("roundId") BigInteger roundId);

    /**
     * 删除轮次成绩
     *
     * @param roundId 轮次id
     * @return 删除信息
     */
    Integer deleteRoundScore(@Param("roundId") BigInteger roundId);

    /**
     * 获取讨论课所属的轮次，无级联，属性完整的
     *
     * @param seminarId 讨论课id
     * @return 轮次
     */
    Round getRoundBySeminar(BigInteger seminarId);// TODO: 2018/12/26
}
