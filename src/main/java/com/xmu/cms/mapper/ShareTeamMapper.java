package com.xmu.cms.mapper;

import com.xmu.cms.entity.ShareTeam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
    List<ShareTeam> getShareInCourse(@Param("courseId") BigInteger courseId);

    /**
     * 删除作为共享队伍或接受课程的组队共享
     *
     * @param courseId 课程id
     * @return 删除共享组队的信息
     */
    Integer deleteShareTeamByCourseId(@Param("courseId") BigInteger courseId);

    /**
     * 删除分享组队
     *
     * @param shareTeamId 分享组队id
     * @return 删除信息
     */
    Integer deleteShareTeam(@Param("shareId") BigInteger shareTeamId);

    /**
     * 新建共享组队
     *
     * @param shareTeam 新的共享组队
     * @return 新建共享组队的id
     */
    Integer insertShareTeam(@Param("shareTeam") ShareTeam shareTeam);

    /**
     * 根据发出共享的课程和收到共享的学生找到共享组队请求
     *
     * @param masterCourseId  发出共享的课程id
     * @param receiveCourseId 收到共享的课程id
     * @return 共享组队
     */
    ShareTeam getShareTeamByTwoCourse(@Param("masterCourseId") BigInteger masterCourseId,
                                      @Param("receiveCourseId") BigInteger receiveCourseId);

    /**
     * 根据老师查找收到共享组队信息
     *
     * @param teacherId 老师id
     * @return 共享组队列表
     */
    List<ShareTeam> getShareTeamByTeacherId(@Param("teacherId") BigInteger teacherId);

    /**
     * 更新共享组队，及接受或拒绝请求
     *
     * @param shareTeam 新的共享组队
     */
    void updateShareTeam(@Param("shareTeam") ShareTeam shareTeam);

    /**
     * 通过id获取共享组队
     *
     * @param shareTeamId 共享组队信息
     * @return 共享组队
     */
    ShareTeam getShareTeam(@Param("shareTeamId") BigInteger shareTeamId);
}
