package com.xmu.cms.mapper;

import com.xmu.cms.entity.ShareTeam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author JuboYu on 2018/12/17.
 * @version 1.0
 */
@Mapper
public interface ShareTeamMapper {

    /**
     * 获取课程有关的所有共享，包括作为主课程和从课程
     * @param courseId 查询课程的id
     * @return 所有的共享信息
     */
    List<ShareTeam> getShareInCourse(Integer courseId);//todo

    /**
     * 删除作为共享队伍或接受课程的组队共享
     * @param courseId 课程id
     * @return 删除共享组队的信息
     */
    Integer deleteShareTeamByCourseId(Integer courseId);
    //todo
}
