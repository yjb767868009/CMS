package com.xmu.cms.dao.Impl;

import com.xmu.cms.dao.SeminarScoreDao;
import com.xmu.cms.mapper.SeminarScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author JuboYu on 2018/12/19.
 * @version 1.0
 */
public class SeminarScoreDaoImpl implements SeminarScoreDao {
    @Autowired
    private SeminarScoreMapper seminarScoreMapper;

}
