package com.xmu.cms.dao;

import com.xmu.cms.entity.Round;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@Mapper
public interface RoundDao {
    List<Round> getRoundsByCourseId(@Param("courseId") Integer courseId);

    Map<String, String> newRound(Integer courseId, Integer roundNum, Integer presentationScoreType, Integer reportScoreType, Integer questionScoreType);
}
