package com.xmu.cms.dao;

import com.xmu.cms.entity.KlassSeminar;
import com.xmu.cms.entity.Round;
import com.xmu.cms.entity.Seminar;
import com.xmu.cms.mapper.KlassMapper;
import com.xmu.cms.mapper.KlassSeminarMapper;
import com.xmu.cms.mapper.RoundMapper;
import com.xmu.cms.mapper.SeminarMapper;
import com.xmu.cms.support.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/12/18.
 * @version 1.0
 */
@Component
public class RoundDao {
    @Autowired
    private RoundMapper roundMapper;

    @Autowired
    private SeminarMapper seminarMapper;

    @Autowired
    private KlassMapper klassMapper;

    @Autowired
    private KlassSeminarMapper klassSeminarMapper;

    public List<Round> getFullRoundsByCourseId(UserInfo info, BigInteger courseId) {
        List<Round> rounds = roundMapper.getRoundsByCourseId(courseId);
        for (Round round : rounds) {
            List<Seminar> seminars = seminarMapper.getAllSeminarByRoundId(round.getRoundId());
            seminars.sort((o1, o2) -> {
                if (o1.getSignOrder() < o2.getSignOrder()) {
                    return -1;
                } else if (o1.getSignOrder() > o2.getSignOrder()) {
                    return 1;
                } else {
                    return 0;
                }
            });
            for (Seminar seminar : seminars) {
                List<KlassSeminar> klassSeminars = new ArrayList<KlassSeminar>();
                switch (info.getUserType()) {
                    case "teacher":
                        klassSeminars = klassSeminarMapper.getKlassSeminarBySeminarId(seminar.getSeminarId());
                        break;
                    case "student":
                        KlassSeminar klassSeminar = klassSeminarMapper.getKlassSeminarByStudentAndSeminar(info.getUserId(), seminar.getSeminarId());
                        klassSeminars.add(klassSeminar);
                        break;
                    default:
                        break;
                }
                seminar.setKlassSeminars(klassSeminars);
            }
            round.setSeminars(seminars);
        }
        return rounds;
    }

    public Round getRoundById(BigInteger roundId) {
        return roundMapper.getRoundByRoundId(roundId);
    }

    public Round getFullRoundById(BigInteger roundId) {
        Round round = roundMapper.getRoundByRoundId(roundId);
        return round;
    }

    public Integer newRound(Round round) {
        Map<BigInteger, Integer> klassEnrollNumber = round.getKlassEnrollNumber();
        for (BigInteger klassId : klassEnrollNumber.keySet()) {
            Integer enrollNumber = klassEnrollNumber.get(klassId);
            klassMapper.insertKlassRound(klassId, round.getRoundId(), enrollNumber);
        }
        return roundMapper.insertRound(round);
    }

    public Integer updateCalType(Round round) {
        return roundMapper.updateCalType(round);
    }

    public List<Round> getRoundByCourseId(BigInteger courseId) {
        return roundMapper.getRoundsByCourseId(courseId);
    }

    public Round getRoundBySeminar(BigInteger seminarId) {
        return roundMapper.getRoundBySeminar(seminarId);
    }
}
