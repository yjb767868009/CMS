package com.xmu.cms.dao.Impl;

import com.xmu.cms.dao.KlassDao;
import com.xmu.cms.entity.Course;
import com.xmu.cms.entity.Klass;
import com.xmu.cms.mapper.CourseMapper;
import com.xmu.cms.mapper.KlassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author JuboYu on 2018/12/16.
 * @version 1.0
 */
@Component
public class KlassDaoImpl implements KlassDao {
    @Autowired
    private KlassMapper klassMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Integer newKlass(Integer courseId, Klass klass) {
        Course course = courseMapper.getCourseById(courseId);
        klass.setCourse(course);
        return klassMapper.insertKlass(klass);
    }

    @Override
    public List<Klass> getAllKlass(Integer courseId) {
        return klassMapper.getAllKlasss(courseId);
    }
}
