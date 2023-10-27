package com.cricket.game.controller;

import com.cricket.game.constants.CricketApiConstants;
import com.cricket.game.entity.TeamEntity;
import com.cricket.game.model.CricketMatchModel;
import com.cricket.game.model.CricketMatchResponseModel;
import com.cricket.game.model.TeamModel;
import com.cricket.game.service.CricketGameService;
import com.cricket.game.service.serviceImpl.CricketGameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(CricketApiConstants.CRICKET_API)
@RestController
public class CricketController {

    @Autowired
    private CricketGameService cricketGameService;

    @PostMapping(CricketApiConstants.GET_MATCH_RESULT)
    public ResponseEntity<CricketMatchResponseModel> getMatchResult(@RequestBody CricketMatchModel match){

        TeamModel teamAResult = cricketGameService.playMatch(match.getTeamA(),match.getTeamB());
        CricketGameServiceImpl.totalSCore=0;
        CricketGameServiceImpl.oldScore=0;
        TeamModel teamBResult = cricketGameService.playMatch(match.getTeamB(),match.getTeamA());
        CricketGameServiceImpl.totalSCore=0;
        CricketGameServiceImpl.oldScore=0;

        CricketMatchResponseModel result = cricketGameService.findMatchResult(teamAResult, teamBResult);
        return  ResponseEntity.ok().body(result);
    }

}
