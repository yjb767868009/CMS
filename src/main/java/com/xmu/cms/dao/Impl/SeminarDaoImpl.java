package com.xmu.cms.dao.Impl;

import com.xmu.cms.dao.SeminarDao;
import com.xmu.cms.entity.Seminar;
import com.xmu.cms.mapper.SeminarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author JuboYu on 2018/12/19.
 * @version 1.0
 */
@Component
public class SeminarDaoImpl implements SeminarDao {
    @Autowired
    private SeminarMapper seminarMapper;

    @Override
    public Integer insertSeminar(Seminar seminar) {
        return seminarMapper.insertSeminar(seminar);
    }

    @Override
    public Integer deleteSeminar(Integer seminarId) {
        return seminarMapper.deleteSeminar(seminarId);
    }

    @Override
    public Integer modifySeminar(Seminar seminar) {
        return seminarMapper.modifySeminar(seminar);
    }

    @Override
    public Integer modifySeminarReportDDL(Seminar seminar) {
        return seminarMapper.modifySeminarReportDDL(seminar);
    }

    @Override
    public Seminar getSeminarBySeminarId(Integer seminarId) {
        return seminarMapper.getSeminarBySeminarId(seminarId);
    }

    @Override
    public Seminar getRunningSeminarByTeacherId(Integer teacherId) {
        return seminarMapper.getRunningSeminarByTeacherId(teacherId);
    }

    @Override
    public List<Seminar> getAllSeminarByRoundId(Integer roundId) {
        return seminarMapper.getAllSeminarByRoundId(roundId);
    }
}
