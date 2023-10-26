package com.cricket.game.controller;

import com.cricket.game.constants.CricketApiConstants;
import com.cricket.game.entity.TeamEntity;
import com.cricket.game.model.CricketMatchModel;
import com.cricket.game.service.CricketGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(CricketApiConstants.CRICKET_API)
@RestController
public class CricketController {

    @Autowired
    private CricketGameService cricketGameService;

    @PostMapping(CricketApiConstants.GET_MATCH_RESULT)
    public ResponseEntity<CricketMatchModel> getMatchResult(@RequestBody CricketMatchModel match){

        TeamEntity teamAResult = cricketGameService.playMatch(match.getTeamA());
        TeamEntity teamBResult = cricketGameService.playMatch(match.getTeamB());

        CricketMatchModel result = cricketGameService.findMatchResult(teamAResult, teamBResult);
        return  ResponseEntity.ok().body(result);
    }

}
