package com.xmu.cms.mapper;

import com.xmu.cms.entity.KlassSeminar;
import org.apache.ibatis.annotations.Param;

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
    KlassSeminar getKlassSeminarByKlassAndSeminar(@Param("klassId") Integer klassId,
                                                  @Param("seminarId") Integer seminarId);

    /**
     * 删除班级讨论课
     *
     * @param klassSeminarId 班级讨论课id
     * @return 删除信息
     */
    Integer deleteKlassSeminar(@Param("klassSeminarId") Integer klassSeminarId);


    /**
     * 删除讨论课成绩
     *
     * @param klassSeminarId 讨论课id
     * @return 删除信息
     */
    Integer deleteKlassSeminarScore(@Param("klassSeminarId") Integer klassSeminarId);
}
