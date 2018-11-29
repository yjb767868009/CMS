package com.xmu.cms.dao;

import com.xmu.cms.entity.Round;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
public interface RoundDao {
    List<Round> getRoundsByCourseId(@Param("courseId") Integer courseId);
}
