package com.cricket.game.service.serviceImpl;

import com.cricket.game.constants.CricketConstants;
import com.cricket.game.model.MatchModel;
import com.cricket.game.model.TeamScoreModel;
import com.cricket.game.service.CricketService;
import com.cricket.game.utils.CricketUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;


@Service
public class CricketServiceImpl implements CricketService {

    public  static int oldScore;
    public  static int  totalSCore ;
    HashMap<String,Integer> batsmanList=null;
    HashMap<String,Integer> bowlerList=new LinkedHashMap<>();

    @Override
    public TeamScoreModel getScoreOfTeam(String team) {

        TeamScoreModel teamModel=new TeamScoreModel();
        batsmanList=new LinkedHashMap<>();

        teamModel.setTeamName(team);
        int wicket = 0;
        int over=0;
        for(int i=1;i<=60&&wicket<10;i++){

            String randomScore = CricketUtils.getRandomScores();
            if(randomScore.equals(CricketConstants.WICKET)){
                wicket=wicket+1;

                setOutBatmanScore(totalSCore,wicket);

            }else {
                totalSCore=totalSCore+Integer.parseInt(randomScore);
            }
            over=i;

        }

        int totalOver = over / 6;
        teamModel.setTotalOver(totalOver);
        teamModel.setTotalWicket(wicket);
        teamModel.setTotalScore(totalSCore);

        setNotOutBatman(totalSCore,wicket);
        teamModel.setBatsmanLIst(batsmanList);

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

    public void setOutBatmanScore(int score,int wicket){

        int newScore=score-oldScore;
        batsmanList.put(CricketConstants.BATSMAN+wicket+CricketConstants.OUT,newScore);
        oldScore=score;

    }

    public  void setNotOutBatman(int totalRemainScore,int wicket) {

        if (wicket < 10) {
            int totalOutScore = batsmanList.values().stream().mapToInt(Integer::intValue).sum();
            int max = totalRemainScore - totalOutScore;

            int min = 1;
            int lastBatsman1;
            int lastBatsman2 = 0;

            Random random = new Random();
            int randomRun = 0;
            if (max != 0) {
                randomRun = random.nextInt((max - min) + 1) + min;
            }
            if (randomRun == max) {

                lastBatsman1 = randomRun;

            } else {

                lastBatsman2 = max - randomRun;
                lastBatsman1 = randomRun;
            }

            batsmanList.put(CricketConstants.BATSMAN + (wicket + 1) + CricketConstants.NOT_OUT, lastBatsman1);
            batsmanList.put(CricketConstants.BATSMAN + (wicket + 2) + CricketConstants.NOT_OUT, lastBatsman2);


        } else if (wicket == 10) {

            int totalOutScore = batsmanList.values().stream().mapToInt(Integer::intValue).sum();
            int max = totalRemainScore - totalOutScore;
            batsmanList.put(CricketConstants.BATSMAN + (wicket + 1) + CricketConstants.NOT_OUT, max);

        }
    }
    

}
