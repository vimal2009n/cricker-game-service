package com.cricket.game.response;

import com.cricket.game.model.ScoreBoardTeamPlayersModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MatchPlayersResponseModel {

    private List<ScoreBoardTeamPlayersModel> teamABatsmanList;
    private List<ScoreBoardTeamPlayersModel> teamABowlerList;

    private List<ScoreBoardTeamPlayersModel> teamBBatsmanList;
    private List<ScoreBoardTeamPlayersModel> teamBBowlerList;
}
