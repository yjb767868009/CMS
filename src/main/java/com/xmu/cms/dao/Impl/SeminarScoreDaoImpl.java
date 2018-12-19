package com.xmu.cms.dao.Impl;

import com.xmu.cms.dao.SeminarScoreDao;
import com.xmu.cms.mapper.SeminarScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author JuboYu on 2018/12/19.
 * @version 1.0
 */
@Component
public class SeminarScoreDaoImpl implements SeminarScoreDao {
    @Autowired
    private SeminarScoreMapper seminarScoreMapper;

}
