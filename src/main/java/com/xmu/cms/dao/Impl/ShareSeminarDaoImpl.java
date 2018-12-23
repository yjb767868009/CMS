package com.xmu.cms.dao.Impl;

import com.xmu.cms.dao.ShareSeminarDao;
import com.xmu.cms.entity.ShareSeminar;
import com.xmu.cms.entity.ShareTeam;
import com.xmu.cms.mapper.ShareSeminarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/20.
 * @version 1.0
 */
@Component
public class ShareSeminarDaoImpl implements ShareSeminarDao {
    @Autowired
    private ShareSeminarMapper shareSeminarMapper;

    @Override
    public List<ShareSeminar> getShareSeminarInCourse(BigInteger courseId) {
        return shareSeminarMapper.getShareSeminarInCourse(courseId);
    }

    @Override
    public Integer deleteShareSeminar(BigInteger seminarShareId) {
        return shareSeminarMapper.deleteShareSeminar(seminarShareId);
    }

    @Override
    public ShareTeam newShareSeminar(ShareSeminar shareSeminar) {
        shareSeminarMapper.insertShareSeminar(shareSeminar);
        BigInteger masterCourseId = shareSeminar.getMasterCourse().getCourseId();
        BigInteger receiveCourseId = shareSeminar.getReceiveCourse().getCourseId();
        return shareSeminarMapper.getShareSeminarByTwoCourse(masterCourseId, receiveCourseId);
    }

    @Override
    public List<ShareSeminar> getShareSeminarByTeacherId(BigInteger teacherId) {
        return shareSeminarMapper.getShareSeminarByTeacher(teacherId);
    }

    @Override
    public ShareSeminar updateShareSeminar(ShareSeminar shareSeminar) {
        shareSeminarMapper.updateShareSeminar(shareSeminar);
        return shareSeminarMapper.getShareSeminar(shareSeminar.getShareSeminarId());
    }
}
