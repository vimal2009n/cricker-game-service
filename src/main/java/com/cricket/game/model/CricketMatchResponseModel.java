package com.cricket.game.model;

import com.cricket.game.entity.TeamEntity;
import lombok.Data;

@Data
public class CricketMatchResponseModel {

    TeamModel teamA;
    TeamModel teamB;
    private String result;
}
