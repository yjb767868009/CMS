package com.xmu.cms.dao;

import com.xmu.cms.entity.Share;

import java.util.List;

/**
 * @author JuboYu on 2018/12/17.
 * @version 1.0
 */
public interface ShareDao {
    List<Share> getShareInCourse(Integer courseId);

    Integer deleteShare(Integer courseId, Integer shareId);
}
