package com.cricket.game.controller;


import com.cricket.game.constants.CricketApiConstants;
import com.cricket.game.model.TeamMapperModel;
import com.cricket.game.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(CricketApiConstants.TEAM_API)
@RestController
public class TeamController {


    @Autowired
    private TeamService teamService;

    @PostMapping(CricketApiConstants.SAVE_TEAM)
    public ResponseEntity<TeamMapperModel> saveTeam(@RequestBody TeamMapperModel model){

        TeamMapperModel team = teamService.saveTeam(model);
        return ResponseEntity.ok().body(team);
    }
    @PutMapping(CricketApiConstants.UPDATE_TEAM)
    public ResponseEntity<TeamMapperModel> updateTeam(@RequestBody TeamMapperModel model){

        TeamMapperModel team = teamService.updateTeam(model);
        return ResponseEntity.ok().body(team);
    }

    @GetMapping(CricketApiConstants.GET_ALL_TEAM)
    public ResponseEntity<List<TeamMapperModel>> getAllTeam(){

        List<TeamMapperModel> list = teamService.getAllTeam();
        return ResponseEntity.ok().body(list);
    }

    @DeleteMapping(CricketApiConstants.DELETE_TEAM_BY_ID)
    public  void deleteTeamById(@RequestParam long teamId){
        teamService.removeTeamById(teamId);
    }

}
