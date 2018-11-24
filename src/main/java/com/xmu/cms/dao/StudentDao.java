package com.xmu.cms.dao;

import com.xmu.cms.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author JuboYu on 2018/11/23.
 * @version 1.0
 */


@Mapper
public interface StudentDao {
    List<Student> getAllStudents();

    String newStudent(Student student);

    String modifyStudent(Student student);

    String deleteStudent(Student student);
}
