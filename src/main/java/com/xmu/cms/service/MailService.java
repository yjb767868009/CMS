package com.xmu.cms.service;

import com.xmu.cms.entity.ShareSeminar;
import com.xmu.cms.entity.ShareTeam;
import com.xmu.cms.entity.TeamApplication;
import com.xmu.cms.support.MyUser;

import java.util.Map;

/**
 * @author JuboYu on 2018/12/5.
 * @version 1.0
 */
public interface MailService {
    Map<String, String> sendPassword(MyUser user);

    void sendShareTeam(ShareTeam newShareTeam);

    void sendShareSeminar(ShareTeam newShareSeminar);

    void sendUpadteShareTeam(ShareTeam newShareTeam);

    void sendUpadteShareSeminar(ShareSeminar newShareSeminar);

    void sendUpdateTeamApplication(TeamApplication newTeamApplication);
}
