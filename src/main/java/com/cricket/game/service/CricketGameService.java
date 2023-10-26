package com.cricket.game.service;

import com.cricket.game.entity.TeamEntity;
import com.cricket.game.model.CricketMatchModel;

public interface CricketGameService {

    TeamEntity playMatch(TeamEntity teamModel);

    CricketMatchModel findMatchResult(TeamEntity teamA, TeamEntity teamB);
}
