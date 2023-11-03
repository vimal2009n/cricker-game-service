package com.cricket.game.service;

import com.cricket.game.model.MatchMapperModel;
import com.cricket.game.request.MatchRequestModel;
import com.cricket.game.response.MatchPlayersResponseModel;
import com.cricket.game.response.PlayerDetailByMatchResponseModel;
import com.cricket.game.response.ScoreBoardResponseModel;

public interface MatchService {

    MatchMapperModel startMatch(MatchRequestModel model);


    void findMatchResult(long matchId);

    ScoreBoardResponseModel getScoreBoardByMatchId(long matchId);

    PlayerDetailByMatchResponseModel getPlayerDetailsByMatchId(long matchId);
}
