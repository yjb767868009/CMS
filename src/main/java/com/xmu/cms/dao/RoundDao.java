package com.xmu.cms.dao;

import com.xmu.cms.entity.KlassEnrollNumber;
import com.xmu.cms.entity.KlassSeminar;
import com.xmu.cms.entity.Round;
import com.xmu.cms.entity.Seminar;
import com.xmu.cms.mapper.*;
import com.xmu.cms.support.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private CourseMapper courseMapper;

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

    public List<Round> getRoundsInCourse(UserInfo info, BigInteger courseId) {
        List<Round> rounds = roundMapper.getRoundsByCourseId(courseId);
        List<Seminar> seminars = seminarMapper.getAllSeminarByCourseId(courseId);
        List<KlassSeminar> klassSeminars = null;
        switch (info.getUserType()) {
            case "teacher":
                klassSeminars = klassSeminarMapper.getKlassSeminarByCourse(courseId);
                break;
            case "student":
                klassSeminars = klassSeminarMapper.getKlassSeminarByStudentAndCourse(info.getUserId(), courseId);
                break;
            default:
                break;
        }
        for (KlassSeminar klassSeminar : klassSeminars) {
            for (Seminar seminar : seminars) {
                if (klassSeminar.getSeminar().getSeminarId().equals(seminar.getSeminarId())) {
                    List<KlassSeminar> klassSeminarList = seminar.getKlassSeminars();
                    if (klassSeminarList == null) {
                        klassSeminarList = new ArrayList<>();
                    }
                    klassSeminarList.add(klassSeminar);
                    seminar.setKlassSeminars(klassSeminarList);
                    break;
                }
            }
        }
        for (Seminar seminar : seminars) {
            for (Round round : rounds) {
                if (seminar.getRound().getRoundId().equals(round.getRoundId())) {
                    List<Seminar> seminarList = round.getSeminars();
                    if (seminarList == null) {
                        seminarList = new ArrayList<>();
                    }
                    seminarList.add(seminar);
                    round.setSeminars(seminarList);
                    break;
                }
            }
        }
        return rounds;
    }

    public Round getRoundById(BigInteger roundId) {
        Round round = roundMapper.getRoundByRoundId(roundId);
        List<KlassEnrollNumber> klassEnrollNumbers = klassMapper.getKlassEnrollNumber(roundId);
        round.setKlassEnrollNumbers(klassEnrollNumbers);
        return round;
    }

    public Round getFullRoundById(BigInteger roundId) {
        Round round = roundMapper.getRoundByRoundId(roundId);
        return round;
    }

    public BigInteger newRound(Round round) {
        List<KlassEnrollNumber> klassEnrollNumbers = round.getKlassEnrollNumbers();
        for (KlassEnrollNumber ken : klassEnrollNumbers) {
            klassMapper.insertKlassRound(ken.getKlass().getKlassId(), round.getRoundId(), ken.getEnrollNumber());
        }
        return BigInteger.valueOf(roundMapper.insertRound(round));
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

    public Round getRoundByKlassSeminar(BigInteger klassSeminarId) {
        return roundMapper.getRoundByKlassSeminarId(klassSeminarId);
    }

    public List<Round> getRoundListInCourse(BigInteger courseId) {
        return roundMapper.getRoundsByCourseId(courseId);
    }
}
