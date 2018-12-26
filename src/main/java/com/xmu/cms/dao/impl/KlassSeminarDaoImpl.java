package com.xmu.cms.dao.impl;

import com.xmu.cms.dao.KlassSeminarDao;
import com.xmu.cms.entity.Klass;
import com.xmu.cms.entity.KlassSeminar;
import com.xmu.cms.mapper.KlassSeminarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JuboYu on 2018/12/21.
 * @version 1.0
 */
@Component
public class KlassSeminarDaoImpl implements KlassSeminarDao {

    @Autowired
    private KlassSeminarMapper klassSeminarMapper;

    @Override
    public List<Klass> getKlassInSeminar(BigInteger seminarId) {
        List<KlassSeminar> klassSeminars = klassSeminarMapper.getKlassSeminarBySeminarId(seminarId);
        List<Klass> klasses = new ArrayList<Klass>();
        for (KlassSeminar klassSeminar : klassSeminars) {
            klasses.add(klassSeminar.getKlass());
        }
        return klasses;
    }

    @Override
    public Integer updateKlassSeminarReportDDL(KlassSeminar klassSeminar) {
        return klassSeminarMapper.updateKlassSeminar(klassSeminar);
    }

    @Override
    public KlassSeminar getKlassSeminarByKlassAndSeminar(BigInteger klassId, BigInteger seminarId) {
        return klassSeminarMapper.getKlassSeminarByKlassAndSeminar(klassId, seminarId);
    }

    @Override
    public KlassSeminar getRunningKlassSeminarByTeacherId(BigInteger teacherId) {
        return klassSeminarMapper.getRunningKlassSeminarByTeacherId(teacherId);
    }

    @Override
    public void stopKlassSeminar(BigInteger klassSeminarId) {
        klassSeminarMapper.stopKlassSeminar(klassSeminarId);
    }

    @Override
    public void startKlassSeminar(BigInteger klassSeminarId) {
        klassSeminarMapper.startKlassSeminar(klassSeminarId);
    }

    @Override
    public void insertKlassSeminar(BigInteger klassId, BigInteger seminarId) {
        klassSeminarMapper.insertKlassSeminar(klassId, seminarId);
    }
}
