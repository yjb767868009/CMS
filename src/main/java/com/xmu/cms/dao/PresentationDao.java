package com.xmu.cms.dao;

import com.xmu.cms.entity.Presentation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@Mapper
public interface PresentationDao {
    public List<Presentation> getPresentationsInSeminar(@Param("seminarId") Integer seminarId);

    Integer setPresentationScore(@Param("presentationId") Integer presentationId,
                                 @Param("presentationScore") Integer presentationScore);
}
