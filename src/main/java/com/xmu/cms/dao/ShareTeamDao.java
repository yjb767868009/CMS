package com.xmu.cms.dao;

import com.xmu.cms.entity.Course;
import com.xmu.cms.entity.ShareTeam;
import com.xmu.cms.mapper.CourseMapper;
import com.xmu.cms.mapper.KlassMapper;
import com.xmu.cms.mapper.ShareTeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/17.
 * @version 1.0
 */
@Component
public class ShareTeamDao {

    @Autowired
    private ShareTeamMapper shareTeamMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private KlassMapper klassMapper;

    public List<ShareTeam> getShareTeamInCourse(BigInteger courseId) {
        return shareTeamMapper.getShareInCourse(courseId);
    }

    public Integer deleteShareTeam(BigInteger shareTeamId) {
        return shareTeamMapper.deleteShareTeam(shareTeamId);
    }

    public ShareTeam newShareTeam(ShareTeam shareTeam) {
        shareTeamMapper.insertShareTeam(shareTeam);
        BigInteger masterCourseId = shareTeam.getMasterCourse().getCourseId();
        BigInteger receiveCourseId = shareTeam.getReceiveCourse().getCourseId();
        return shareTeamMapper.getShareTeamByTwoCourse(masterCourseId, receiveCourseId);
    }

    public List<ShareTeam> getShareTeamByTeacherId(BigInteger teacherId) {
        return shareTeamMapper.getShareTeamByTeacherId(teacherId);
    }

    public ShareTeam updateShareTeam(ShareTeam shareTeam) {
        shareTeamMapper.updateShareTeam(shareTeam);
        ShareTeam newShareTeam = shareTeamMapper.getShareTeam(shareTeam.getShareTeamId());

        Course masterCourse = shareTeam.getMasterCourse();
        Course receiveCourse = shareTeam.getReceiveCourse();

        masterCourse = courseMapper.getCourseById(masterCourse.getCourseId());
        receiveCourse = courseMapper.getCourseById(receiveCourse.getCourseId());

        newShareTeam.setMasterCourse(masterCourse);
        newShareTeam.setReceiveCourse(receiveCourse);
        return newShareTeam;
    }
}
