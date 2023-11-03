package com.cricket.game.controller;

import com.cricket.game.constants.CricketApiConstants;
import com.cricket.game.model.MatchMapperModel;
import com.cricket.game.request.MatchRequestModel;
import com.cricket.game.response.MatchPlayersResponseModel;
import com.cricket.game.response.PlayerDetailByMatchResponseModel;
import com.cricket.game.response.ScoreBoardResponseModel;
import com.cricket.game.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(CricketApiConstants.CRICKET_API)
@RestController
public class MatchController {
    
    @Autowired
    private MatchService matchService;
    
    @PostMapping(CricketApiConstants.PLAY_MATCH)
    public ResponseEntity<MatchMapperModel> playMatch(@RequestBody MatchRequestModel model){

        MatchMapperModel matchModel = matchService.startMatch(model);
        return ResponseEntity.ok().body(matchModel);

    }

    @GetMapping(CricketApiConstants.GET_MATCH_DETAILS_BY_MATCH_ID)
    public ResponseEntity<ScoreBoardResponseModel> getPayerListByMatchId(@RequestBody MatchMapperModel match){

        ScoreBoardResponseModel response = matchService.getScoreBoardByMatchId(match.getId());
         return ResponseEntity.ok().body(response);
    }

    @GetMapping(CricketApiConstants.GET_PLAYER_DETAILS_BY_MATCH_ID)
    public ResponseEntity<PlayerDetailByMatchResponseModel> getPlayerDetailsByMatchId(@RequestBody MatchMapperModel matchId){

        PlayerDetailByMatchResponseModel response = matchService.getPlayerDetailsByMatchId(matchId.getId());
        return ResponseEntity.ok().body(response);
    }
}
