package com.cricket.game.service.serviceImpl;

import com.cricket.game.constants.CricketConstants;
import com.cricket.game.entity.PlayerEntity;
import com.cricket.game.entity.TeamEntity;
import com.cricket.game.model.CricketMatchResponseModel;
import com.cricket.game.model.TeamModel;
import com.cricket.game.service.CricketGameService;
import com.cricket.game.utils.CricketUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CricketGameServiceImpl implements CricketGameService {


    public  static int oldScore;
    public  static int  totalSCore ;
    HashMap<String,Integer> batsmanList=null;

    @Override
    public TeamModel playMatch(TeamEntity teamA,TeamEntity teamB) {


        TeamModel teamModel=new TeamModel();
        teamModel.setTeamName(teamA.getTeamName());
        teamModel.setBattingTeam(teamA.getTeamName());
        teamModel.setBowlingTeam(teamB.getTeamName());
        batsmanList=new LinkedHashMap<>();

        int numberOfWicket = teamA.getPlayers().size();

        int wicket = 0;
        int over=0;
        for(int i = 1; i<=60&&wicket<numberOfWicket; i++){

            String randomScore = CricketUtils.getRandomScores();
            if(randomScore.equals(CricketConstants.WICKET)){
                wicket=wicket+1;


                String batsman = CricketUtils.getRandomBatsMan(teamA.getPlayers(), wicket);
               if(batsman!=null) {
                    setOutBatmanScore(totalSCore,batsman);
               }

            }else {
                totalSCore=totalSCore+Integer.parseInt(randomScore);
            }
            over=i;

        }

        int totalOver = over / 6;
        teamModel.setTotalOver(totalOver);
        teamModel.setTotalWicket(wicket);
        teamModel.setTotalScore(totalSCore);


        teamModel.setBatsmanLIst(batsmanList);
        setNotOutBatman(totalSCore,wicket,teamA.getPlayers());

        List<String> bowlers = getBowlersList(wicket, 20, teamB.getPlayers());
        teamModel.setBowlersList(bowlers);
        return teamModel;
    }

    @Override
    public CricketMatchResponseModel findMatchResult(TeamModel teamA, TeamModel teamB) {
        String result;
        CricketMatchResponseModel matchResult=new CricketMatchResponseModel();
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


    public void setOutBatmanScore(int score,String batsman){

        int newScore=score-oldScore;
        batsmanList.put(batsman+CricketConstants.OUT ,newScore);
        oldScore=score;

    }


    public  void setNotOutBatman(int totalRemainScore,int wicket,List<PlayerEntity> team){


        int teamSize = team.size();

        if(wicket<teamSize) {
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

            String batsman1 = CricketUtils.getRandomBatsMan(team,(wicket-1)+1);
            batsmanList.put(batsman1 + CricketConstants.NOT_OUT, lastBatsman1);
            String batsman2 = CricketUtils.getRandomBatsMan(team,(wicket-1)+2);
            batsmanList.put(batsman2 + CricketConstants.NOT_OUT, lastBatsman2);


        } else if (wicket == teamSize) {

            int totalOutScore = batsmanList.values().stream().mapToInt(Integer::intValue).sum();
            int max = totalRemainScore - totalOutScore;

            String batsman = CricketUtils.getRandomBatsMan(team,(wicket-1) + 1);
            batsmanList.put(batsman +  CricketConstants.NOT_OUT, max);
        }

    }


    public  List<String> getBowlersList(int totalWicket, int totalOver, List<PlayerEntity> teamB){

        List<String > bowlersList=new LinkedList<>();
        int numberOfBowlers = totalOver / 4;
        Random random=new Random();

        if(!teamB.isEmpty()) {

            for (int i = 0; i < numberOfBowlers; i++) {

                int wicket = random.nextInt(totalWicket + 1);
                bowlersList.add(teamB.get(i).getName() + CricketConstants.WICKET_TAKEN + wicket);
                totalWicket -= wicket;
            }

        }
        return bowlersList;
    }



}
