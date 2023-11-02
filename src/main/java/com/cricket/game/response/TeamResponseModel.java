package com.cricket.game.response;

import com.cricket.game.model.ScoreBoardTeamPlayersModel;
import lombok.Data;

import java.util.List;

@Data
public class TeamResponseModel {


    private long id;
    private String teamName;
    private int totalRun;
    private int totalWicket;
    private int totalOver;

    private List<ScoreBoardTeamPlayersModel> batmanList;
    private List<ScoreBoardTeamPlayersModel> bowlerList;


}
