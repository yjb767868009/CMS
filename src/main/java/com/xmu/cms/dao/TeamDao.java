package com.xmu.cms.dao;

import com.xmu.cms.entity.Team;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author JuboYu on 2018/11/27.
 * @version 1.0
 */
@Mapper
public interface TeamDao {

    List<Team> getAllTeamsInSeminar(@Param("seminarId") Integer seminarId);

    List<Team> getAllTeamsInClbumSeminar(@Param("seminarId") Integer clbumSeminarId);

    Team getTeamByTeamId(Integer teamId);

    Team getTeamInClbumByStudentId(Integer clbumId, Integer studentId);
}
