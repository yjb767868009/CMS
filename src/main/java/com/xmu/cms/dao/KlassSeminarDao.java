package com.xmu.cms.dao;

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
public class KlassSeminarDao {

    @Autowired
    private KlassSeminarMapper klassSeminarMapper;

    public List<Klass> getKlassInSeminar(BigInteger seminarId) {
        List<KlassSeminar> klassSeminars = klassSeminarMapper.getKlassSeminarBySeminarId(seminarId);
        List<Klass> klasses = new ArrayList<Klass>();
        for (KlassSeminar klassSeminar : klassSeminars) {
            klasses.add(klassSeminar.getKlass());
        }
        return klasses;
    }

    public Integer updateKlassSeminarReportDDL(KlassSeminar klassSeminar) {
        return klassSeminarMapper.updateKlassSeminar(klassSeminar);
    }

    public KlassSeminar getKlassSeminarByKlassAndSeminar(BigInteger klassId, BigInteger seminarId) {
        return klassSeminarMapper.getKlassSeminarByKlassAndSeminar(klassId, seminarId);
    }

    public List<KlassSeminar> getRunningKlassSeminarByTeacherId(BigInteger teacherId) {
        return klassSeminarMapper.getRunningKlassSeminarByTeacherId(teacherId);
    }

    public void endKlassSeminar(BigInteger klassSeminarId) {
        klassSeminarMapper.endKlassSeminar(klassSeminarId);
    }

    public void startKlassSeminar(BigInteger klassSeminarId) {
        klassSeminarMapper.startKlassSeminar(klassSeminarId);
    }

    public BigInteger insertKlassSeminar(BigInteger klassId, BigInteger seminarId) {
        return BigInteger.valueOf(klassSeminarMapper.insertKlassSeminar(klassId, seminarId));
    }

    public KlassSeminar getKlassSeminar(BigInteger klassSeminarId) {
        return klassSeminarMapper.getKlassSeminar(klassSeminarId);
    }
}
