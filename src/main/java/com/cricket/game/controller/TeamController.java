package com.cricket.game.controller;


import com.cricket.game.constants.CricketApiConstants;
import com.cricket.game.exception.CricketGameException;
import com.cricket.game.model.TeamMapperModel;
import com.cricket.game.service.TeamService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(CricketApiConstants.TEAM_API)
@RestController
public class TeamController {


    @Autowired
    private TeamService teamService;

    @PostMapping(CricketApiConstants.SAVE_TEAM)
    public ResponseEntity<Object> saveTeam(@RequestBody TeamMapperModel model){

        try {
            TeamMapperModel team = teamService.saveTeam(model);
            return ResponseEntity.ok().body(team);
        }catch (ConstraintViolationException e){

            return getConstraintViolationMessage(e);
        }catch (CricketGameException e){
            return ResponseEntity.ok().body(e.getMessage());
        }
    }
    @PutMapping(CricketApiConstants.UPDATE_TEAM)
    public ResponseEntity<Object> updateTeam(@RequestBody TeamMapperModel model){
        try {
            TeamMapperModel team = teamService.updateTeam(model);
            return ResponseEntity.ok().body(team);
        }catch (ConstraintViolationException e){

            return getConstraintViolationMessage(e);
        }
    }

    @GetMapping(CricketApiConstants.GET_ALL_TEAM)
    public ResponseEntity<Object> getAllTeam(){

        try {
            List<TeamMapperModel> list = teamService.getAllTeam();
            return ResponseEntity.ok().body(list);
        }catch (Exception e){

            return ResponseEntity.ok().body(e.getMessage());
        }

    }

    @DeleteMapping(CricketApiConstants.DELETE_TEAM_BY_ID)
    public  void deleteTeamById(@RequestParam long teamId){
        teamService.removeTeamById(teamId);
    }

    private ResponseEntity<Object> getConstraintViolationMessage(ConstraintViolationException e){
        List<String> errorMessage = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errorMessage.get(0).toString());
    }

}
