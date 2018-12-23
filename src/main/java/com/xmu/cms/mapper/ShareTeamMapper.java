package com.xmu.cms.mapper;

import com.xmu.cms.entity.ShareTeam;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/17.
 * @version 1.0
 */
@Mapper
public interface ShareTeamMapper {

    /**
     * 获取课程有关的所有共享，包括作为主课程和从课程
     *
     * @param courseId 查询课程的id
     * @return 所有的共享信息
     */
    List<ShareTeam> getShareInCourse(BigInteger courseId);

    /**
     * 删除作为共享队伍或接受课程的组队共享
     *
     * @param courseId 课程id
     * @return 删除共享组队的信息
     */
    Integer deleteShareTeamByCourseId(BigInteger courseId);

    /**
     * 删除分享组队
     *
     * @param shareTeamId 分享组队id
     * @return 删除信息
     */
    Integer deleteShareTeam(BigInteger shareTeamId);

    /**
     * 新建共享组队
     *
     * @param shareTeam 新的共享组队
     */
    void insertShareTeam(ShareTeam shareTeam);// TODO: 2018/12/23

    /**
     * 根据发出共享的课程和收到共享的学生找到共享组队请求
     *
     * @param masterCourseId  发出共享的课程id
     * @param receiveCourseId 收到共享的课程id
     * @return 共享组队
     */
    ShareTeam getShareTeamByTwoCourse(BigInteger masterCourseId, BigInteger receiveCourseId);// TODO: 2018/12/23
}
