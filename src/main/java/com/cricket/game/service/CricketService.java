package com.cricket.game.service;


import com.cricket.game.model.MatchModel;
import com.cricket.game.model.TeamScoreModel;

import java.util.regex.MatchResult;

public interface CricketService {

     TeamScoreModel getScoreOfTeam(String team);

     MatchModel findMatchResult(TeamScoreModel team1, TeamScoreModel team2);
}
