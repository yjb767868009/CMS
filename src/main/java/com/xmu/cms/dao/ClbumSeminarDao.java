package com.xmu.cms.dao;

import com.xmu.cms.entity.ClbumSeminar;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author JuboYu on 2018/12/5.
 * @version 1.0
 */
@Mapper
public interface ClbumSeminarDao {

    ClbumSeminar getClbumSeminarById(Integer clbumSeminarId);
}
