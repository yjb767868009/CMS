package com.xmu.cms.service;

import com.xmu.cms.entity.Presentation;

import java.util.List;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
public interface PresentationService {
    List<Presentation> getPresentationsInSeminar(Integer seminarId);

    String setPresentationScore(Integer presentationId, Integer presentationScore);
}
