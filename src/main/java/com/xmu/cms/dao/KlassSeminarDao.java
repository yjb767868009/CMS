package com.xmu.cms.dao;

import com.xmu.cms.entity.Klass;
import com.xmu.cms.entity.KlassSeminar;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/21.
 * @version 1.0
 */
public interface KlassSeminarDao {
    List<Klass> getKlassInSeminar(BigInteger seminarId);

    Integer updateKlassSeminarReportDDL(KlassSeminar klassSeminar);

    KlassSeminar getKlassSeminarByKlassAndSeminar(BigInteger klassId, BigInteger seminarId);

    KlassSeminar getRunningKlassSeminarByTeacherId(BigInteger teacherId);

    void stopKlassSeminar(BigInteger klassSeminarId);
}
