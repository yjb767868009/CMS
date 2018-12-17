package com.xmu.cms.mapper;

import com.xmu.cms.entity.Share;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author JuboYu on 2018/12/17.
 * @version 1.0
 */
@Mapper
public interface ShareMapper {

    /**
     * 获取课程有关的所有共享，包括作为主课程和从课程
     * @param courseId 查询课程的id
     * @return 所有的共享信息
     */
    List<Share> getShareInCourse(Integer courseId);
    //todo
}
