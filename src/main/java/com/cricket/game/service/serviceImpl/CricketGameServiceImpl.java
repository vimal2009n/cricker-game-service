package com.cricket.game.service.serviceImpl;

import com.cricket.game.constants.CricketConstants;
import com.cricket.game.entity.TeamEntity;
import com.cricket.game.model.CricketMatchModel;
import com.cricket.game.service.CricketGameService;
import com.cricket.game.utils.CricketUtils;
import org.springframework.stereotype.Service;

@Service
public class CricketGameServiceImpl implements CricketGameService {

    @Override
    public TeamEntity playMatch(TeamEntity team) {
        TeamEntity teamModel=new TeamEntity();
        teamModel.setTeamName(team.getTeamName());
        int wicket = 0;
        int over=0;
        long totalSCore = 0;
        for(int i = 1; i<=60&&wicket<10; i++){

            String randomScore = CricketUtils.getRandomScores();
            if(randomScore.equals(CricketConstants.WICKET)){
                wicket=wicket+1;

            }else {
                totalSCore=totalSCore+Integer.parseInt(randomScore);
            }
            over=i;

        }

        int totalOver = over / 6;
        teamModel.setTotalOver(totalOver);
        teamModel.setTotalWicket(wicket);
        teamModel.setTotalScore(totalSCore);
        return teamModel;
    }

    @Override
    public CricketMatchModel findMatchResult(TeamEntity teamA, TeamEntity teamB) {
        String result;
        CricketMatchModel matchResult=new CricketMatchModel();
        if(teamA.getTotalScore()>teamB.getTotalScore()){

            result=teamA.getTeamName()+ CricketConstants.WON_THE_MATCH;

        }else  {

            result=teamB.getTeamName()+CricketConstants.WON_THE_MATCH;
        }
        matchResult.setResult(result);
        matchResult.setTeamA(teamA);
        matchResult.setTeamB(teamB);
        return matchResult;
    }
}
