package com.cricket.game.model;

import com.cricket.game.entity.TeamEntity;
import lombok.Data;

@Data
public class CricketMatchModel {

    TeamEntity teamA;
    TeamEntity teamB;
    private String result;
}
