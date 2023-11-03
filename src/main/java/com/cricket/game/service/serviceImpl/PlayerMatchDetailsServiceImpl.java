package com.cricket.game.service.serviceImpl;

import com.cricket.game.entity.BallTrackingEntity;
import com.cricket.game.entity.MatchEntity;
import com.cricket.game.entity.PlayerMatchDetailsEntity;
import com.cricket.game.entity.Players;
import com.cricket.game.repository.BallTrackingRepository;
import com.cricket.game.repository.MatchRepository;
import com.cricket.game.repository.PlayerMatchDetailsRepository;
import com.cricket.game.repository.PlayerRepository;
import com.cricket.game.service.PlayerMatchDetailsService;
import com.cricket.game.service.PlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerMatchDetailsServiceImpl implements PlayerMatchDetailsService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private PlayerMatchDetailsRepository playerMatchDetailsRepository;

    @Autowired
    private BallTrackingRepository ballTrackingRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public void generatePlayerDetailsByMatch() {

        List<MatchEntity> allMatchDetails = matchRepository.findAll();


        if(!allMatchDetails.isEmpty()){
              allMatchDetails.forEach(match->{

                List<PlayerMatchDetailsEntity> matchList = playerMatchDetailsRepository.findByMatchIdId(match.getId());
                if(matchList.isEmpty()){

                    List<Players> teamAPlayers = playerRepository.findByTeamId(match.getTeamAId());


                    if(!teamAPlayers.isEmpty()) {
                        addPlayerDetailsOnPlayerMatchDetailEntity(teamAPlayers,  match);
                    }

                    List<Players> teamBPlayers = playerRepository.findByTeamId(match.getTeamBId());

                    if(!teamBPlayers.isEmpty()) {
                        addPlayerDetailsOnPlayerMatchDetailEntity(teamBPlayers,  match);
                    }

                }

            });

        }

    }

    void addPlayerDetailsOnPlayerMatchDetailEntity(List<Players> teamPlayers,MatchEntity match){

        teamPlayers.stream().forEach(player->{

            PlayerMatchDetailsEntity playerMatchEntity=new PlayerMatchDetailsEntity();
            playerMatchEntity.setMatchId(match);
            playerMatchEntity.setPlayerId(player);
            playerMatchEntity.setTeam(player.getTeam());

            List<BallTrackingEntity> batsManList = ballTrackingRepository.findByMatchIdIdAndBatsManId(match.getId(), player.getId());

            if(!batsManList.isEmpty()) {
                int totalRun = batsManList.stream().mapToInt(BallTrackingEntity::getRun).sum();
                playerMatchEntity.setRunScored(totalRun);

            }


            List<BallTrackingEntity> bowlerList = ballTrackingRepository.findByMatchIdIdAndBowlerId(match.getId(), player.getId());
            if(!bowlerList.isEmpty()){

                int totalWicket = bowlerList.stream().mapToInt(BallTrackingEntity::getWicket).sum();
                playerMatchEntity.setTotalOverBowled(bowlerList.size());
                playerMatchEntity.setTakenWicket(totalWicket);
            }

            if(playerMatchEntity.getRunScored()!=0||playerMatchEntity.getTakenWicket()!=0) {
                playerMatchDetailsRepository.save(playerMatchEntity);
            }
        });
    }




}
