package com.xmu.cms.mapper;

import com.xmu.cms.entity.KlassSeminar;

/**
 * @author JuboYu on 2018/12/20.
 * @version 1.0
 */
public interface KlassSeminarMapper {
    /**
     * 通过班级和讨论课获取班级对应的讨论课
     *
     * @param klassId   班级id
     * @param seminarId 讨论课id
     * @return 班级讨论课
     */
    KlassSeminar getKlassSeminarByKlassAndSeminar(Integer klassId, Integer seminarId);//todo

    /**
     * 删除班级讨论课
     *
     * @param klassSeminarId 班级讨论课id
     * @return 删除信息
     */
    Integer deleteKlassSeminar(Integer klassSeminarId);//todo
}
