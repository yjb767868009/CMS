package com.xmu.cms.dao.Impl;

import com.xmu.cms.dao.SeminarDao;
import com.xmu.cms.entity.KlassSeminar;
import com.xmu.cms.entity.Seminar;
import com.xmu.cms.mapper.KlassSeminarMapper;
import com.xmu.cms.mapper.SeminarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/19.
 * @version 1.0
 */
@Component
public class SeminarDaoImpl implements SeminarDao {
    @Autowired
    private SeminarMapper seminarMapper;

    @Autowired
    private KlassSeminarMapper klassSeminarMapper;

    @Override
    public Integer insertSeminar(Seminar seminar) {
        return seminarMapper.insertSeminar(seminar);
    }

    @Override
    public Integer deleteSeminar(BigInteger seminarId) {
        return seminarMapper.deleteSeminar(seminarId);
    }

    @Override
    public Integer modifySeminar(Seminar seminar) {
        List<KlassSeminar> klassSeminars = seminar.getKlassSeminars();
        for (KlassSeminar klassSeminar : klassSeminars) {
            klassSeminarMapper.updateKlassSeminar(klassSeminar);
        }
        return seminarMapper.modifySeminar(seminar);
    }

    @Override
    public Seminar getSeminarBySeminarId(BigInteger seminarId) {
        Seminar seminar = seminarMapper.getSeminarBySeminarId(seminarId);
        //List<KlassSeminar> klassSeminars = klassSeminarMapper.getKlassSeminarBySeminarId(seminarId);
        //seminar.setKlassSeminars(klassSeminars);
        return seminar;
    }

    @Override
    public KlassSeminar getRunningSeminarByTeacherId(BigInteger teacherId) {
        return seminarMapper.getRunningKlassSeminarByTeacherId(teacherId);
    }

    @Override
    public List<Seminar> getAllSeminarByRoundId(BigInteger roundId) {
        return seminarMapper.getAllSeminarByRoundId(roundId);
    }
}
