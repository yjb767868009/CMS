package com.xmu.cms.dao;

import com.xmu.cms.entity.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author JuboYu on 2018/11/23.
 * @version 1.0
 */
@Mapper
public interface StudentDao {
    public Student getAllStudents();
}
