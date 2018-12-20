package com.xmu.cms.dao.Impl;

import com.xmu.cms.dao.ShareTeamDao;
import com.xmu.cms.entity.ShareTeam;
import com.xmu.cms.mapper.ShareTeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author JuboYu on 2018/12/17.
 * @version 1.0
 */
@Component
public class ShareTeamDaoImpl implements ShareTeamDao {

    @Autowired
    private ShareTeamMapper shareTeamMapper;

    @Override
    public List<ShareTeam> getShareInCourse(Integer courseId) {
        return shareTeamMapper.getShareInCourse(courseId);
    }

    @Override
    public Integer deleteShare(Integer courseId, Integer shareId) {
        //todo
        return null;
    }
}
