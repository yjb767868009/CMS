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

    void sendShareTeam(ShareTeam shareTeam) throws Exception;

    void sendShareSeminar(ShareSeminar shareSeminar) throws Exception;

    void sendTeamApplication(TeamApplication teamApplication) throws Exception;

    void sendUpdateShareTeam(ShareTeam shareTeam) throws Exception;

    void sendUpdateShareSeminar(ShareSeminar shareSeminar) throws Exception;

    void sendUpdateTeamApplication(TeamApplication teamApplication) throws Exception;
}
