package com.cricket.game.controller;

import com.cricket.game.constants.CricketApiConstants;
import com.cricket.game.model.PlayersMapperModel;
import com.cricket.game.service.PlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(CricketApiConstants.PLAYER_API)
@RestController
public class PlayerController {

    @Autowired
    private PlayersService playersService;

    @PostMapping(CricketApiConstants.SAVE_PLAYER)
    ResponseEntity<PlayersMapperModel> savePlayer(@RequestBody PlayersMapperModel model){

        PlayersMapperModel responseModel = playersService.savePlayer(model);
        return ResponseEntity.ok().body(responseModel);
    }

    @GetMapping(CricketApiConstants.GET_ALL_PLAYER)
    ResponseEntity<List<PlayersMapperModel>> getAllPlayers(){

        List<PlayersMapperModel> allPlayers = playersService.getAllPlayer();
        return ResponseEntity.ok().body(allPlayers);
    }

    @GetMapping(CricketApiConstants.GET_PLAYER_BY_TEAM_ID)
    ResponseEntity<List<PlayersMapperModel>> getPlayersByTeamId(@RequestParam long teamId){

        List<PlayersMapperModel> playersListByTeamId = playersService.getPlayersByTeamId(teamId);
        return ResponseEntity.ok().body(playersListByTeamId);
    }

    @DeleteMapping(CricketApiConstants.DELETE_PLAYER_BY_ID)
    public void deletePlayerById(@RequestParam long playerId){
        playersService.deletePlayerByID(playerId);
    }


}
