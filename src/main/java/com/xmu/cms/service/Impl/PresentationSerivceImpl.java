package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.PresentationDao;
import com.xmu.cms.entity.Presentation;
import com.xmu.cms.service.PresentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@Service
public class PresentationSerivceImpl implements PresentationService {
    @Autowired
    private PresentationDao presentationDao;

    @Override
    public List<Presentation> getPresentationsInSeminar(Integer seminarId) {
        return presentationDao.getPresentationsInSeminar(seminarId);
    }

    @Override
    public String setPresentationScore(Integer presentationId, Integer presentationScore) {
        Integer count = presentationDao.setPresentationScore(presentationId, presentationScore);
        if (count == 1) {
            return "Success";
        } else {
            return "Set Score Error";
        }
    }

}
