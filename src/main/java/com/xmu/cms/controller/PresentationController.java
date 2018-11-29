package com.xmu.cms.controller;

import com.xmu.cms.aspect.CheckTeacherPermission;
import com.xmu.cms.service.PresentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author JuboYu on 2018/11/29.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/api/presentation/{presentationId}")
public class PresentationController {
    @Autowired
    private PresentationService presentationService;

    @PatchMapping(value = "/setScore")
    @CheckTeacherPermission
    public String setPresentationScore(@PathVariable("presentationId") Integer presentationId,
                                       @RequestParam("presentationScore") Integer presentationScore) {
        return presentationService.setPresentationScore(presentationId, presentationScore);
    }
}
