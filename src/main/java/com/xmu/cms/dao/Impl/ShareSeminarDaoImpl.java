package com.xmu.cms.dao.Impl;

import com.xmu.cms.dao.ShareSeminarDao;
import com.xmu.cms.entity.ShareSeminar;
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
        return shareSeminarMapper.getShareSeminar(courseId);
    }
}
