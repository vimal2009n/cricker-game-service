package com.cricket.game.model;

import com.cricket.game.entity.TeamEntity;
import lombok.Data;

@Data
public class CricketMatchRequestModel {

    private TeamModel teamA;
    private TeamModel teamB;
    private String result;

    private int totalOver;
}
