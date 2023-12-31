package com.cricket.game.controller;

import com.cricket.game.constants.CricketApiConstants;
import com.cricket.game.model.PlayersMapperModel;
import com.cricket.game.service.PlayersService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(CricketApiConstants.PLAYER_API)
@RestController
public class PlayerController {

    @Autowired
    private PlayersService playersService;

    @PostMapping(CricketApiConstants.SAVE_PLAYER)
    ResponseEntity<Object> savePlayer( @RequestBody PlayersMapperModel model){

        try {
            PlayersMapperModel responseModel = playersService.savePlayer(model);
            return ResponseEntity.ok().body(responseModel);
        }catch (ConstraintViolationException e){
            return getConstraintViolationMessage(e);
        }

    }
    @PutMapping(CricketApiConstants.UPDATE_PLAYER)
    ResponseEntity<Object> updatePlayer(@RequestBody PlayersMapperModel model){

        try {
            PlayersMapperModel responseModel = playersService.updatePlayer(model);
            return ResponseEntity.ok().body(responseModel);
        }catch (ConstraintViolationException e){
            return getConstraintViolationMessage(e);
        }

    }

    @GetMapping(CricketApiConstants.GET_ALL_PLAYER)
    ResponseEntity<Object> getAllPlayers(){

        try {
            List<PlayersMapperModel> allPlayers = playersService.getAllPlayer();
            return ResponseEntity.ok().body(allPlayers);
        }catch (Exception e){
            return ResponseEntity.ok().body(e.getMessage());
        }

    }

    @GetMapping(CricketApiConstants.GET_PLAYER_BY_TEAM_ID)
    ResponseEntity<Object> getPlayersByTeamId(@RequestParam long teamId){

        try {
            List<PlayersMapperModel> playersListByTeamId = playersService.getPlayersByTeamId(teamId);
            return ResponseEntity.ok().body(playersListByTeamId);
        }catch (Exception e){
            return ResponseEntity.ok().body(e.getMessage());
        }

    }

    @DeleteMapping(CricketApiConstants.DELETE_PLAYER_BY_ID)
    public void deletePlayerById(@RequestParam long playerId){
        playersService.deletePlayerByID(playerId);
    }

    private ResponseEntity<Object> getConstraintViolationMessage(ConstraintViolationException e){
        List<String> errorMessage = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errorMessage.get(0).toString());
    }

}
