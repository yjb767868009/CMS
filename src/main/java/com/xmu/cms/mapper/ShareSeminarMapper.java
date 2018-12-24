package com.xmu.cms.mapper;

import com.xmu.cms.entity.ShareSeminar;
import org.apache.ibatis.annotations.Mapper;

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
    Integer deleteShareSeminarByCourseId(BigInteger courseId);

    /**
     * 获取课程中所有共享讨论课
     *
     * @param courseId 课程id
     * @return 共享讨论课列表
     */
    List<ShareSeminar> getShareSeminarInCourse(BigInteger courseId);// TODO: 2018/12/23 冲突被改名了

    /**
     * 删除指定共享分组
     *
     * @param seminarShareId 共享分组id
     * @return 删除信息
     */
    Integer deleteShareSeminar(BigInteger seminarShareId);// TODO: 2018/12/23

    /**
     * 新增分享讨论课
     *
     * @param shareSeminar 新增的共享讨论课
     */
    void insertShareSeminar(ShareSeminar shareSeminar);// TODO: 2018/12/23

    /**
     * 根据发出共享的课程和收到共享的学生找到共享讨论课请求
     *
     * @param masterCourseId  发出共享的课程id
     * @param receiveCourseId 收到共享的课程id
     * @return 共享组信息
     */
    ShareSeminar getShareSeminarByTwoCourse(BigInteger masterCourseId, BigInteger receiveCourseId);// TODO: 2018/12/23

    /**
     * 根据老师查询收到的未处理的共享讨论课信息
     *
     * @param teacherId 老师id
     * @return 共享讨论课列表
     */
    List<ShareSeminar> getShareSeminarByTeacher(BigInteger teacherId);// TODO: 2018/12/23

    /**
     * 更新共享讨论课，及接受或拒绝请求
     *
     * @param shareSeminar 新的共享讨论课
     */
    void updateShareSeminar(ShareSeminar shareSeminar);// TODO: 2018/12/23

    /**
     * 获取共享讨论课
     *
     * @param shareSeminarId 共享讨论课id
     * @return 共享讨论课
     */
    ShareSeminar getShareSeminar(BigInteger shareSeminarId);// TODO: 2018/12/23
}
