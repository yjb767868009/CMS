package com.xmu.cms.mapper;

import com.xmu.cms.entity.TeamApplication;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/23.
 * @version 1.0
 */
@Mapper
public interface TeamApplicationMapper {
    /**
     * 根据老师获取收到的队伍合法申请
     *
     * @param teacherId 老师id
     * @return 队伍合法申请列表
     */
    List<TeamApplication> getTeamApplicationByTeacherId(BigInteger teacherId);// TODO: 2018/12/23

    /**
     * 更新队伍合法申请，及接受或拒绝请求
     *
     * @param teamApplication 新的队伍合法申请
     */
    void updateTeamApplication(TeamApplication teamApplication);// TODO: 2018/12/23  

    /**
     * 获取队伍合法申请
     *
     * @param teamApplicationId 队伍合法申请id
     * @return 队伍合法申请
     */
    TeamApplication getTeamApplication(BigInteger teamApplicationId);// TODO: 2018/12/23  
}
