package com.cricket.game.serviceImpl;

import com.cricket.game.constants.CricketConstants;
import com.cricket.game.model.MatchModel;
import com.cricket.game.model.TeamScoreModel;
import com.cricket.game.service.CricketService;
import com.cricket.game.utils.CricketUtils;
import org.springframework.stereotype.Service;


@Service
public class CricketServiceImpl implements CricketService {


    @Override
    public TeamScoreModel getScoreOfTeam(String team) {

        TeamScoreModel teamModel=new TeamScoreModel();
        teamModel.setTeamName(team);
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
    public MatchModel findMatchResult(TeamScoreModel team1, TeamScoreModel team2) {

        String result;
        MatchModel matchResult=new MatchModel();
        if(team1.getTotalScore()>team2.getTotalScore()){

            result=team1.getTeamName()+ CricketConstants.WON_THE_MATCH;

        }else  {

            result=team2.getTeamName()+CricketConstants.WON_THE_MATCH;
        }
        matchResult.setResult(result);
        matchResult.setTeam1(team1);
        matchResult.setTeam2(team2);
        return matchResult;
    }


}
