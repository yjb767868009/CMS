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
    List<ShareSeminar> getShareSeminar(BigInteger courseId);
}
