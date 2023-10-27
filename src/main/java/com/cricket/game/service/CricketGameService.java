package com.cricket.game.service;

import com.cricket.game.entity.TeamEntity;
import com.cricket.game.model.CricketMatchResponseModel;
import com.cricket.game.model.TeamModel;

public interface CricketGameService {

    TeamModel playMatch(TeamEntity teamAModel,TeamEntity teamBModel);
    CricketMatchResponseModel findMatchResult(TeamModel teamA, TeamModel teamB);
}
