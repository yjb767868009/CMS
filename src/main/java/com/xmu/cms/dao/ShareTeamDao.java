package com.xmu.cms.dao;

import com.xmu.cms.entity.ShareTeam;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/17.
 * @version 1.0
 */
public interface ShareTeamDao {
    List<ShareTeam> getShareTeamInCourse(BigInteger courseId);

    Integer deleteShare(BigInteger courseId, BigInteger shareId);
}
