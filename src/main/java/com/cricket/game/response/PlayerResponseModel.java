package com.cricket.game.response;

import com.cricket.game.model.ScoreBoardTeamPlayersModel;
import lombok.Data;

import java.util.List;

@Data
public class PlayerResponseModel {

    private List<ScoreBoardTeamPlayersModel> batmanList;
    private List<ScoreBoardTeamPlayersModel> bowlerList;
}
