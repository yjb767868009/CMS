package com.xmu.cms.dao;

import com.xmu.cms.entity.Round;
import com.xmu.cms.entity.RoundScore;
import com.xmu.cms.entity.SeminarScore;
import com.xmu.cms.entity.Team;
import com.xmu.cms.mapper.RoundMapper;
import com.xmu.cms.mapper.RoundScoreMapper;
import com.xmu.cms.mapper.SeminarScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 * @author JuboYu on 2018/12/18.
 * @version 1.0
 */
@Component
public class RoundScoreDao {

    @Autowired
    private RoundScoreMapper roundScoreMapper;

    @Autowired
    private SeminarScoreMapper seminarScoreMapper;

    @Autowired
    private RoundMapper roundMapper;

    public RoundScore getRoundTeamScore(BigInteger roundId, BigInteger teamId) {
        return roundScoreMapper.getTeamScoreInRound(roundId, teamId);
    }

    public List<RoundScore> getRoundScore(BigInteger roundId) {
        return roundScoreMapper.getAllScoresInRound(roundId);
    }

    public void updateRoundScore(BigInteger klassSeminarId, BigInteger teamId) {
        Round round = roundMapper.getRoundByKlassSeminarId(klassSeminarId);
        RoundScore roundScore = new RoundScore();
        roundScore.setRound(round);
        roundScore.setTeam(new Team(teamId));
        List<SeminarScore> seminarScores = seminarScoreMapper.getTeamSeminarScoreInRound(teamId, klassSeminarId);
        Float presentationScore = (float) 0;
        Float reportScore = (float) 0;
        Float questionScore = (float) 0;
        for (SeminarScore oneSeminarScore : seminarScores) {
            Float seminarPresentationScore = oneSeminarScore.getPresentationScore();
            if (round.getPresentationScoreType() == 0) {
                presentationScore += seminarPresentationScore;
            } else if (round.getPresentationScoreType() == 1 && seminarPresentationScore > presentationScore) {
                presentationScore = seminarPresentationScore;
            }

            Float seminarReportScore = oneSeminarScore.getReportScore();
            if (round.getReportScoreType() == 0) {
                reportScore += seminarPresentationScore;
            } else if (round.getReportScoreType() == 1 && seminarReportScore > reportScore) {
                reportScore = seminarReportScore;
            }


            Float seminarQuestionScore = oneSeminarScore.getQuestionScore();
            if (round.getQuestionScoreType() == 0) {
                questionScore += seminarQuestionScore;
            } else if (round.getQuestionScoreType() == 1 && seminarQuestionScore > questionScore) {
                questionScore = seminarQuestionScore;
            }
        }
        if (round.getPresentationScoreType() == 0) {
            presentationScore /= seminarScores.size();
        }
        roundScore.setPresentationScore(presentationScore);

        if (round.getReportScoreType() == 0) {
            reportScore /= seminarScores.size();
        }
        roundScore.setReportScore(reportScore);

        if (round.getQuestionScoreType() == 0) {
            questionScore /= seminarScores.size();
        }
        roundScore.setQuestionScore(questionScore);

        roundScoreMapper.updateRoundScore(roundScore);
    }

}
