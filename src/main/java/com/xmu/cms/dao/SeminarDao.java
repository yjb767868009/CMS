package com.xmu.cms.dao;

import com.xmu.cms.entity.KlassSeminar;
import com.xmu.cms.entity.Seminar;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/19.
 * @version 1.0
 */
public interface SeminarDao {
    Integer insertSeminar(Seminar seminar);

    Integer deleteSeminar(BigInteger seminarId);

    Integer modifySeminar(Seminar seminar);

    Seminar getSeminarBySeminarId(BigInteger seminarId);

    KlassSeminar getRunningSeminarByTeacherId(BigInteger teacherId);

    List<Seminar> getAllSeminarByRoundId(BigInteger roundId);
}
