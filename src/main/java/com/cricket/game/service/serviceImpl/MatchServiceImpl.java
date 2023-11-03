package com.cricket.game.service.serviceImpl;

import com.cricket.game.constants.CricketConstants;
import com.cricket.game.entity.*;
import com.cricket.game.mapper.MatchMapper;
import com.cricket.game.model.MatchMapperModel;
import com.cricket.game.model.ScoreBoardTeamPlayersModel;
import com.cricket.game.repository.*;
import com.cricket.game.request.MatchRequestModel;
import com.cricket.game.response.*;
import com.cricket.game.service.MatchService;
import com.cricket.game.utils.CricketUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private InningsDetailRepository inningsDetailRepository;

    @Autowired
    private InningsPerOverRepository inningsPerOverRepository;

    @Autowired
    private BallTrackingRepository ballTrackingRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MatchMapper matchMapper;

    @Override
    public MatchMapperModel  startMatch(MatchRequestModel model) {


        int totalBall= model.getTotalOver()*6;
        //********************** Match  *****************
        MatchEntity  matchEntity=new MatchEntity();
        matchEntity.setTeamAId(model.getTeamAId());
        matchEntity.setTeamBId(model.getTeamBId());
        matchEntity.setMatchDate(CricketUtils.getCurrentDate());
        matchEntity.setTotalOver(model.getTotalOver());
        matchEntity.setVenue(model.getVenue());
        MatchEntity newMatch = matchRepository.save(matchEntity);


        //first Innings
        playMatchByInnings(newMatch, model.getTeamAId(), model.getTeamBId(),1,totalBall);
        //second Innings
        playMatchByInnings(newMatch, model.getTeamBId(), model.getTeamAId(),2,totalBall);

        // finding the result of the match
        findMatchResult(newMatch.getId());

        MatchMapperModel matchModel = matchMapper.mapMatchEntityToMatchModel(newMatch);


        return matchModel;
    }



public void ballTrackingProcess(List<Players> batsManList,List<Players> bowlerList,MatchEntity newMatch,
                                InningsDetailsEntity newInningsDetails,InningsPerOverEntity newInningsPerOver,int totalBall){


    List<Long>  batsmanIdList= batsManList.stream().map(f -> f.getId()).collect(Collectors.toList());
    List<Long>  bowlerIdList= bowlerList.stream().map(f -> f.getId()).collect(Collectors.toList());
    int totalOver=totalBall/6;

    int totalWicket=batsmanIdList.size();

    int numberOfOver=0;
    int wicket = 0;
    for(int i = 1; i<=totalBall&&wicket<totalWicket; i++) {

        BallTrackingEntity ballTrackingEntity=new BallTrackingEntity();
        ballTrackingEntity.setMatchId(newMatch);
        ballTrackingEntity.setInningsId(newInningsDetails);
        ballTrackingEntity.setInningsPerOverId(newInningsPerOver);

        //************ loop ***************

        //  add and remove bowler
        long currentBowler = CricketUtils.getPlayerFromList(bowlerIdList);
        ballTrackingEntity.setBowlerId(currentBowler);

        List<Integer> overList = CricketUtils.getMultipleOfSix(totalOver);
        if(overList.contains(i)){
            CricketUtils.removePlayerFromList(bowlerIdList,currentBowler);

           /* numberOfOver=numberOfOver+1;
            newInningsPerOver.setNumberOfOver(numberOfOver);

            inningsPerOverRepository.save(newInningsPerOver);*/
        }


        // set batsman
        long currentBatsmanId = CricketUtils.getPlayerFromList(batsmanIdList);
        ballTrackingEntity.setBatsManId(currentBatsmanId);


        //**************************

        String randomScore = CricketUtils.getRandomScores();
        if (randomScore.equals(CricketConstants.WICKET)) {

            ballTrackingEntity.setWicket(1);
            wicket=wicket+1;
            CricketUtils.removePlayerFromList(batsmanIdList,currentBatsmanId);
            numberOfOver=numberOfOver+1;

        } else {

            int run = Integer.parseInt(randomScore);
            ballTrackingEntity.setRun(run);

        }
        ballTrackingRepository.save(ballTrackingEntity);

    }
}


public void updateInningsDetails(MatchEntity newMatch,InningsDetailsEntity newInningsDetails,InningsPerOverEntity newInningsPerOver){


    List<BallTrackingEntity> allBallList = ballTrackingRepository
            .findByInningsIdIdAndInningsPerOverIdId(newInningsDetails.getId(), newInningsPerOver.getId());
    updateInningsPerOver(allBallList,newInningsPerOver);


    List<InningsPerOverEntity> inningsPerOverList = inningsPerOverRepository.findByMatchIdIdAndInningsIdId(newMatch.getId(), newInningsPerOver.getInningsId().getId());
    int totalOverRun = inningsPerOverList.stream().mapToInt(InningsPerOverEntity::getTotalRunTaken).sum();

    int totalOverWicket = inningsPerOverList.stream().mapToInt(InningsPerOverEntity::getWicketPerOver).sum();


    newInningsDetails.setTotalScore(totalOverRun);
    newInningsDetails.setTotalWicket(totalOverWicket);
    newInningsDetails.setTotalOver(inningsPerOverList.size());

    inningsDetailRepository.save(newInningsDetails);
}



public void playMatchByInnings(MatchEntity  newMatch,long battingTeamId,long bowlingTeamId,int inningsNumber,int totalBall){

    List<Players> batsManList = playerRepository.findByTeamId(battingTeamId);
    List<Players> bowlerList = playerRepository.findByTeamId(bowlingTeamId);

    //********************** Innings details ****************************

    InningsDetailsEntity inningsDetails=new InningsDetailsEntity();
    inningsDetails.setMatchId(newMatch);
    inningsDetails.setBattingTeamId(battingTeamId);
    inningsDetails.setBowlingTeamId(bowlingTeamId);
    inningsDetails.setInningsNumber(inningsNumber);
    InningsDetailsEntity newInningsDetails = inningsDetailRepository.save(inningsDetails);


    //******************************* innings per over ************************************

    InningsPerOverEntity inningsPerOverEntity=new InningsPerOverEntity();
    inningsPerOverEntity.setMatchId(newMatch);
    inningsPerOverEntity.setInningsId(newInningsDetails);
    inningsPerOverEntity.setInningsNumber(inningsNumber);
    InningsPerOverEntity newInningsPerOver = inningsPerOverRepository.save(inningsPerOverEntity);


    //******************************* ball tracking ************************************

    //start ball tracking entity
    ballTrackingProcess(batsManList,bowlerList,newMatch,newInningsDetails,newInningsPerOver,totalBall);
    //update Inning details
    updateInningsDetails(newMatch,newInningsDetails,newInningsPerOver);

}






//**************************************************************


    public void  updateInningsPerOver(List<BallTrackingEntity> allBallList,InningsPerOverEntity newInningsPerOver){
        if(!allBallList.isEmpty()) {

            int over=0;
            for (int i = 0; i < allBallList.size(); i += 6) {



                int run = allBallList.subList(i, Math.min(i + 6, allBallList.size()))
                        .stream().mapToInt(BallTrackingEntity::getRun).sum();

                int totalWicket = allBallList.subList(i, Math.min(i + 6, allBallList.size()))
                        .stream().mapToInt(BallTrackingEntity::getWicket).sum();

                if(i==6) {

                    over=over+1;

                    newInningsPerOver.setBowlerId(allBallList.get(i).getBowlerId());
                    newInningsPerOver.setTotalRunTaken(run);
                    newInningsPerOver.setWicketPerOver(totalWicket);

                    newInningsPerOver.setNumberOfOver(over);

                    inningsPerOverRepository.save(newInningsPerOver);

                }else {

                    over=over+1;
                    InningsPerOverEntity entity = new InningsPerOverEntity();
                    entity.setBowlerId(allBallList.get(i).getBowlerId());
                    entity.setTotalRunTaken(run);
                    entity.setInningsId(allBallList.get(i).getInningsId());
                    entity.setMatchId(allBallList.get(i).getMatchId());
                    entity.setWicketPerOver(totalWicket);
                    entity.setInningsNumber(newInningsPerOver.getInningsNumber());

                    entity.setNumberOfOver(over);

                    inningsPerOverRepository.save(entity);

                }
            }
        }
    }



    @Override
    public void findMatchResult(long matchId) {


        List<InningsDetailsEntity> teamList = inningsDetailRepository.findByMatchIdId(matchId);
        if(!teamList.isEmpty()){

            InningsDetailsEntity teamA = teamList.get(0);
            InningsDetailsEntity teamB = teamList.get(1);

            Optional<MatchEntity> matchEntity = matchRepository.findById(matchId);
            if(matchEntity.isPresent()){

                if(teamA.getTotalScore()>teamB.getTotalScore()){

                    matchEntity.get().setWinnerTeamId(teamA.getBattingTeamId());


                }else  {
                     matchEntity.get().setWinnerTeamId(teamB.getBattingTeamId());
                }


                Optional<Team> teamDetails = teamRepository.findById(matchEntity.get().getWinnerTeamId());
                if(teamDetails.isPresent()){

                    matchEntity.get().setMatchSummary(teamDetails.get().getTeamName()+CricketConstants.WON_THE_MATCH);

                }
                matchRepository.save(matchEntity.get());

            }

        }

    }

    //*********************** batsman and bowler list *****************

    @Override
    public  ScoreBoardResponseModel getScoreBoardByMatchId(long matchId){

        ScoreBoardResponseModel responseModel=new ScoreBoardResponseModel();


        Optional<MatchEntity> matchDetails = matchRepository.findById(matchId);
        if(matchDetails.isPresent()){


            // Team A
            List<Players> teamAList = playerRepository.findByTeamId(matchDetails.get().getTeamAId());
            InningsDetailsEntity teamAInningsDetails = inningsDetailRepository.findByMatchIdIdAndBattingTeamId(matchId, matchDetails.get().getTeamAId());
            TeamResponseModel teamADetails = getBatsManAndBowlerDetailsFromPlayerLis(teamAList, matchId);

            List<ScoreBoardTeamPlayersModel> teamABowlers = teamADetails.getBowlerList();

            //team B *****

            List<Players> teamBList = playerRepository.findByTeamId(matchDetails.get().getTeamBId());
            InningsDetailsEntity teamBInningsDetails = inningsDetailRepository.findByMatchIdIdAndBattingTeamId(matchId, matchDetails.get().getTeamBId());
            TeamResponseModel teamBDetails = getBatsManAndBowlerDetailsFromPlayerLis(teamBList, matchId);

            List<ScoreBoardTeamPlayersModel> teamBBowlers = teamBDetails.getBowlerList();

            // Team A
            teamADetails.setId(matchDetails.get().getTeamAId());

            Optional<Team> teamA = teamRepository.findById(matchDetails.get().getTeamAId());
            if(teamA.isPresent()) {
                teamADetails.setTeamName(teamA.get().getTeamName());
                teamADetails.setTotalRun(teamAInningsDetails.getTotalScore());
                teamADetails.setTotalOver(teamAInningsDetails.getTotalOver());
                teamADetails.setTotalWicket(teamAInningsDetails.getTotalWicket());
                teamADetails.setBowlerList(teamBBowlers);
            }


            //team B *****
            teamBDetails.setId(matchDetails.get().getTeamBId());
            Optional<Team> teamB = teamRepository.findById(matchDetails.get().getTeamBId());
            if(teamB.isPresent()) {
                teamBDetails.setTeamName(teamB.get().getTeamName());
                teamBDetails.setTotalRun(teamBInningsDetails.getTotalScore());
                teamBDetails.setTotalOver(teamBInningsDetails.getTotalOver());
                teamBDetails.setTotalWicket(teamBInningsDetails.getTotalWicket());
                teamBDetails.setBowlerList(teamABowlers);

            }

            responseModel.setResult(matchDetails.get().getMatchSummary());
            responseModel.setTeamA(teamADetails);
            responseModel.setTeamB(teamBDetails);



        }
        return responseModel;

    }


    public TeamResponseModel getBatsManAndBowlerDetailsFromPlayerLis( List<Players> teamList,long matchId){

        TeamResponseModel teamResponseModel=new TeamResponseModel();

        List<ScoreBoardTeamPlayersModel> teamBatsmanList=new ArrayList<>();
        List<ScoreBoardTeamPlayersModel> teamBowlerList=new ArrayList<>();

        if(!teamList.isEmpty()){

            teamList.forEach(f->{
                //batsman
                List<BallTrackingEntity> batsmanList = ballTrackingRepository.findByMatchIdIdAndBatsManId(matchId, f.getId());
                if(!batsmanList.isEmpty()){

                    ScoreBoardTeamPlayersModel batsMan=new ScoreBoardTeamPlayersModel();
                    int totalRun = batsmanList.stream().mapToInt(BallTrackingEntity::getRun).sum();

                    batsMan.setId(f.getId());
                    batsMan.setPlayerName(f.getPlayerName());
                    batsMan.setRun(totalRun);
                    teamBatsmanList.add(batsMan);

                }
                //bowler

                List<BallTrackingEntity> bowlerList = ballTrackingRepository.findByMatchIdIdAndBowlerId(matchId, f.getId());
                if(!bowlerList.isEmpty()){


                    int totalWicket = bowlerList.stream().mapToInt(BallTrackingEntity::getWicket).sum();

                    if(totalWicket!=0) {
                        ScoreBoardTeamPlayersModel bowler = new ScoreBoardTeamPlayersModel();
                        bowler.setId(f.getId());
                        bowler.setPlayerName(f.getPlayerName());
                        bowler.setTotalWicket(totalWicket);
                        teamBowlerList.add(bowler);
                    }

                }

            });

        }

        teamResponseModel.setBatmanList(teamBatsmanList);
        teamResponseModel.setBowlerList(teamBowlerList);

        return teamResponseModel;

    }

    @Override
    public PlayerDetailByMatchResponseModel getPlayerDetailsByMatchId(long matchId){

        PlayerDetailByMatchResponseModel responseModel=new PlayerDetailByMatchResponseModel();
        Optional<MatchEntity> matchDetails = matchRepository.findById(matchId);
        if(matchDetails.isPresent()) {


            //Team A
            PlayerResponseModel teamAPlayerModel=new PlayerResponseModel();

            List<Players> teamAList = playerRepository.findByTeamId(matchDetails.get().getTeamAId());
            InningsDetailsEntity teamAInningsDetails = inningsDetailRepository.findByMatchIdIdAndBattingTeamId(matchId, matchDetails.get().getTeamAId());
            TeamResponseModel teamADetails = getBatsManAndBowlerDetailsFromPlayerLis(teamAList, matchId);
            teamAPlayerModel.setBatmanList(teamADetails.getBatmanList());
            teamAPlayerModel.setBowlerList(teamADetails.getBowlerList());
            responseModel.setTeamAPlayers(teamAPlayerModel);

            //Team B
            PlayerResponseModel teamBPlayerModel=new PlayerResponseModel();
            List<Players> teamBList = playerRepository.findByTeamId(matchDetails.get().getTeamBId());
            InningsDetailsEntity teamBInningsDetails = inningsDetailRepository.findByMatchIdIdAndBattingTeamId(matchId, matchDetails.get().getTeamBId());
            TeamResponseModel teamBDetails = getBatsManAndBowlerDetailsFromPlayerLis(teamAList, matchId);
            teamBPlayerModel.setBatmanList(teamBDetails.getBatmanList());
            teamBPlayerModel.setBowlerList(teamBDetails.getBowlerList());
            responseModel.setTeamBPlayers(teamBPlayerModel);
        }
        return responseModel;
    }


}
