package com.xmu.cms.mapper;

import com.xmu.cms.entity.Question;
import com.xmu.cms.entity.SeminarScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * @author BiqunZhou on 2018/12/5.
 * @version 2.0
 */
@Mapper
public interface SeminarScoreMapper {
    /**
     * 删除讨论课成绩
     *
     * @param seminarId 讨论课id
     */
    void deleteSeminarScore(@Param("seminarId") BigInteger seminarId);

    /**
     * 获取轮次中队伍的所有讨论课成绩
     *
     * @param teamId  队伍id
     * @param roundId 轮次id
     * @return 讨论课成绩列表
     */
    List<SeminarScore> getTeamSeminarScoreInRound(@Param("teamId") BigInteger teamId, @Param("roundId") BigInteger roundId);

    /**
     * 根据队伍讨论课，修改对应的讨论课成绩
     *
     * @param seminarScore 讨论课成绩
     */
    void modifyTeamSeminarScore(@Param("seminarScore") SeminarScore seminarScore);


    /**
     * 根据队伍和讨论课给报告打分
     *
     * @param seminarScore 讨论课成绩，包含队伍id，讨论课id，报告成绩
     */
    void updateReportScore(@Param("seminarScore") SeminarScore seminarScore);

    /**
     * 根据队伍和讨论课给展示打分
     *
     * @param seminarScore 讨论课成绩，包含队伍id，讨论课id，展示成绩
     */
    void updatePresentationScore(@Param("seminarScore") SeminarScore seminarScore);

    /**
     * 获取队伍在讨论课的成绩
     *
     * @param seminarId 讨论课id
     * @param teamId    队伍id
     * @return 讨论课成绩
     */
    SeminarScore getSeminarTeamScore(@Param("seminarId") BigInteger seminarId,
                                     @Param("teamId") BigInteger teamId);

    /**
     * 获取讨论课中所有讨论课成绩，所有team
     *
     * @param seminarId 讨论课id
     * @return 讨论课成绩列表
     */
    List<SeminarScore> getSeminarScoreInSeminar(@Param("seminarId") BigInteger seminarId);

    /**
     * 修改成绩的提问成绩
     *
     * @param question 提问
     */
    void updateQuestionScore(@Param("question") Question question);
}
