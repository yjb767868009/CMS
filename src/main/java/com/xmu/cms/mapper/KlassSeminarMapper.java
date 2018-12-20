package com.xmu.cms.mapper;

import com.xmu.cms.entity.KlassSeminar;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;

/**
 * @author JuboYu on 2018/12/20.
 * @version 1.0
 */
@Mapper
public interface KlassSeminarMapper {
    /**
     * 通过班级和讨论课获取班级对应的讨论课
     *
     * @param klassId   班级id
     * @param seminarId 讨论课id
     * @return 班级讨论课
     */
    KlassSeminar getKlassSeminarByKlassAndSeminar(BigInteger klassId, BigInteger seminarId);//todo

    /**
     * 删除班级讨论课
     *
     * @param klassSeminarId 班级讨论课id
     * @return 删除信息
     */
    Integer deleteKlassSeminar(BigInteger klassSeminarId);//todo
}
