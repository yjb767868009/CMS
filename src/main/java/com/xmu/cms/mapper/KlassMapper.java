package com.xmu.cms.mapper;

import com.xmu.cms.entity.Klass;
import com.xmu.cms.entity.Seminar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@Mapper
public interface KlassMapper {
    Integer insertKlass(Klass klass);

    List<Seminar> getStudentKlassByCourseId(@Param("studentId") Integer studentId,
                                            @Param("courseId") Integer courseId);
                                            
    Integer deleteKlassByKlassId(@Param("klassId") Integer klassId);

    List<Klass> getAllKlasss(@Param("courseId") Integer courseId);
}
