package com.xmu.cms.dao;

import com.xmu.cms.entity.Course;
import com.xmu.cms.entity.ShareSeminar;
import com.xmu.cms.mapper.CourseMapper;
import com.xmu.cms.mapper.KlassMapper;
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
public class ShareSeminarDao {
    @Autowired
    private ShareSeminarMapper shareSeminarMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private KlassMapper klassMapper;

    public List<ShareSeminar> getShareSeminarInCourse(BigInteger courseId) {
        return shareSeminarMapper.getShareSeminarInCourse(courseId);
    }

    public Integer deleteShareSeminar(BigInteger seminarShareId) {
        return shareSeminarMapper.deleteShareSeminar(seminarShareId);
    }

    public ShareSeminar newShareSeminar(ShareSeminar shareSeminar) {
        shareSeminarMapper.insertShareSeminar(shareSeminar);
        BigInteger masterCourseId = shareSeminar.getMasterCourse().getCourseId();
        BigInteger receiveCourseId = shareSeminar.getReceiveCourse().getCourseId();
        return shareSeminarMapper.getShareSeminarByTwoCourse(masterCourseId, receiveCourseId);
    }

    public List<ShareSeminar> getShareSeminarByTeacherId(BigInteger teacherId) {
        return shareSeminarMapper.getShareSeminarByTeacher(teacherId);
    }

    public ShareSeminar updateShareSeminar(ShareSeminar shareSeminar) {
        shareSeminarMapper.updateShareSeminar(shareSeminar);
        ShareSeminar newShareSeminar = shareSeminarMapper.getShareSeminar(shareSeminar.getShareSeminarId());

        Course masterCourse = shareSeminar.getMasterCourse();
        Course receiveCourse = shareSeminar.getReceiveCourse();

        masterCourse = courseMapper.getCourseById(masterCourse.getCourseId());
        receiveCourse = courseMapper.getCourseById(receiveCourse.getCourseId());

        newShareSeminar.setMasterCourse(masterCourse);
        newShareSeminar.setReceiveCourse(receiveCourse);
        return newShareSeminar;
    }
}
