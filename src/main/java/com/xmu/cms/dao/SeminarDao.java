package com.xmu.cms.dao;

import com.xmu.cms.entity.Seminar;

import java.util.List;

/**
 * @author JuboYu on 2018/12/19.
 * @version 1.0
 */
public interface SeminarDao {
    Integer insertSeminar(Seminar seminar);

    Integer deleteSeminar(Integer seminarId);

    Integer modifySeminar(Seminar seminar);

    Integer modifySeminarReportDDL(Seminar seminar);

    Seminar getSeminarBySeminarId(Integer seminarId);

    Seminar getRunningSeminarByTeacherId(Integer teacherId);

    List<Seminar> getAllSeminarByRoundId(Integer roundId);
}
