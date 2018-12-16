package com.xmu.cms.service;

import com.xmu.cms.entity.Klass;

import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
public interface KlassService {
    Map<String, String> newKlass(Integer courseId, Klass klass);

    List<Klass> getKlassInCourse(Integer courseId);
}
