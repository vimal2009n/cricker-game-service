package com.cricket.game.model;

import lombok.Data;

@Data
public class MatchModel {

    private TeamScoreModel team1;
    private TeamScoreModel team2;
    private String result;
}
