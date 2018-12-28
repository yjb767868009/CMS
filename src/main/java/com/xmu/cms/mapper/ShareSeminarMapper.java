package com.xmu.cms.mapper;

import com.xmu.cms.entity.ShareSeminar;
import com.xmu.cms.entity.ShareTeam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/20.
 * @version 1.0
 */
@Mapper
public interface ShareSeminarMapper {
    /**
     * 删除作为共享课程或接受课程的讨论课分享
     *
     * @param courseId 课程id
     * @return 删除信息
     */
    Integer deleteShareSeminarByCourseId(@Param("courseId") BigInteger courseId);

    /**
     * 获取课程中所有共享讨论课
     *
     * @param courseId 课程id
     * @return 共享讨论课列表
     */
    List<ShareSeminar> getShareSeminarInCourse(@Param("courseId") BigInteger courseId);

    /**
     * 删除指定共享分组
     *
     * @param seminarShareId 共享分组id
     * @return 删除信息
     */
    Integer deleteShareSeminar(@Param("shareId") BigInteger seminarShareId);

    /**
     * 新增分享讨论课
     *
     * @param shareSeminar 新增的共享讨论课
     */
    BigInteger insertShareSeminar(@Param("shareSeminar") ShareSeminar shareSeminar);

    /**
     * 根据发出共享的课程和收到共享的课程找到共享讨论课请求
     *
     * @param masterCourseId  发出共享的课程id
     * @param receiveCourseId 收到共享的课程id
     * @return 共享组信息
     */
    ShareSeminar getShareSeminarByTwoCourse(@Param("masterCourseId") BigInteger masterCourseId,
                                            @Param("receiveCourseId") BigInteger receiveCourseId);

    /**
     * 根据老师查询收到的未处理的共享讨论课信息
     *
     * @param teacherId 老师id
     * @return 共享讨论课列表
     */
    List<ShareSeminar> getShareSeminarByTeacher(@Param("teacherId") BigInteger teacherId);

    /**
     * 更新共享讨论课，及接受或拒绝请求
     *
     * @param shareSeminar 新的共享讨论课
     */
    void updateShareSeminar(@Param("shareSeminar") ShareSeminar shareSeminar);

    /**
     * 获取共享讨论课
     *
     * @param shareSeminarId 共享讨论课id
     * @return 共享讨论课
     */
    ShareSeminar getShareSeminar(@Param("shareId") BigInteger shareSeminarId);
}
