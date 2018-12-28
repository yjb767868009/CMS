package com.xmu.cms.dao;

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
public class SeminarDao {
    @Autowired
    private SeminarMapper seminarMapper;

    @Autowired
    private KlassSeminarMapper klassSeminarMapper;

    public BigInteger insertSeminar(Seminar seminar) {
        return BigInteger.valueOf(seminarMapper.insertSeminar(seminar));
    }

    public Integer deleteSeminar(BigInteger seminarId) {
        return seminarMapper.deleteSeminar(seminarId);
    }

    public Integer modifySeminar(Seminar seminar) {
        List<KlassSeminar> klassSeminars = seminar.getKlassSeminars();
        for (KlassSeminar klassSeminar : klassSeminars) {
            klassSeminarMapper.updateKlassSeminar(klassSeminar);
        }
        return seminarMapper.modifySeminar(seminar);
    }

    public Seminar getSeminarBySeminarId(BigInteger seminarId) {
        return seminarMapper.getSeminarBySeminarId(seminarId);
    }

    public List<Seminar> getAllSeminarByRoundId(BigInteger roundId) {
        return seminarMapper.getAllSeminarByRoundId(roundId);
    }

    public List<Seminar> getSeminarByCourseId(BigInteger courseId) {
        return seminarMapper.getAllSeminarByCourseId(courseId);
    }
}
