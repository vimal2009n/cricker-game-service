package com.cricket.game.controller;

import com.cricket.game.constants.CricketApiConstants;
import com.cricket.game.model.MatchModel;
import com.cricket.game.model.TeamScoreModel;
import com.cricket.game.service.CricketService;
import com.cricket.game.service.serviceImpl.CricketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(CricketApiConstants.CRICKET_API)
@RestController
public class CricketController {

    @Autowired
    private CricketService cricketService;

    @GetMapping(CricketApiConstants.GET_MATCH_RESULT)
    public ResponseEntity<MatchModel> getMatchResult(@RequestParam String team1, @RequestParam String team2){

        TeamScoreModel team1Result = cricketService.getScoreOfTeam(team1);
        CricketServiceImpl.totalSCore=0;
        CricketServiceImpl.oldScore=0;
        TeamScoreModel team2Result = cricketService.getScoreOfTeam(team2);
        MatchModel result = cricketService.findMatchResult(team1Result, team2Result);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
