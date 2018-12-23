package com.xmu.cms.controller;

import com.xmu.cms.entity.ShareSeminar;
import com.xmu.cms.entity.ShareTeam;
import com.xmu.cms.entity.Team;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="")
public class RequestController {


    @GetMapping(value="/request/teamshare")
    public List<ShareTeam> getTeamShareList(){
        //TODO 获得队伍共享申请信息列表
        return null;
    }

    @GetMapping(value="/request/seminarshare")
    public List<ShareSeminar> getSeminarShareList(){
        //TODO 获得讨论课共享申请信息列表
        return null;
    }

    @GetMapping(value="/request/teamshare/{teamshareId}")
    public ShareTeam getTeamShare(@PathVariable("teamshareId") Integer teamshareId){
        //TODO 获得某一个队伍共享申请信息
        return null;
    }

    @PutMapping(value="/request/teamshare/{teamshareId}")
    public Map<String,String> updateTeamShare(@PathVariable("teamshareId") Integer teamshareId,
                                              @RequestBody ShareTeam shareTeam){
        //TODO 按ID修改共享请求状态
        return null;
    }

    @GetMapping(value="/request/seminarshare/{seminarshareId}")
    public ShareSeminar getSeminarShare(@PathVariable("seminarshareId") Integer seminarshareId){
        //TODO 获得某一个讨论课共享申请信息
        return null;
    }

    @PutMapping(value="/request/seminarshare/{seminarshareId}")
    public Map<String,String> updateSeminarShare(@PathVariable("seminarshareId") Integer seminarshareId,
                                                 @RequestBody ShareSeminar shareSeminar){
        //TODO 按ID修改共享请求状态
        return null;
    }

    @GetMapping(value="request/teamvalid")
    public List<Team> getTeamValidList(){
        //TODO 获得组队申请信息列表
        return null;
    }

    @GetMapping(value="request/teamvalid/{teamvalidId}")
    public Team getTeamValid(@PathVariable("teamvalidId") Integer teamvalidId){
        //TODO 获得某一个组队申请信息列表
        return null;
    }

    @PutMapping(value="request/teamvalid/{teamvalidId}")
    public Map<String,String> updateTeamValid(@PathVariable("teamvalidId") Integer teamvalidId,
                                              @RequestBody Team team){
        //TODO 按id修改组队请求状态
        return null;
    }
}
