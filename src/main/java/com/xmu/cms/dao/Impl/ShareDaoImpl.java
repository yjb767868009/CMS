package com.xmu.cms.dao.Impl;

import com.xmu.cms.dao.ShareDao;
import com.xmu.cms.entity.Share;
import com.xmu.cms.mapper.ShareMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author JuboYu on 2018/12/17.
 * @version 1.0
 */
@Component
public class ShareDaoImpl implements ShareDao {

    @Autowired
    private ShareMapper shareMapper;

    @Override
    public List<Share> getShareInCourse(Integer courseId) {
        return shareMapper.getShareInCourse(courseId);
    }

    @Override
    public Integer deleteShare(Integer courseId, Integer shareId) {
        //todo
        return null;
    }
}
