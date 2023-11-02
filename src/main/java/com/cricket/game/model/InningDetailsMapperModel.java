package com.cricket.game.model;

import com.cricket.game.entity.MatchEntity;
import lombok.Data;

@Data
public class InningDetailsMapperModel {

    private long id;
    private int inningsNumber;
    private long battingTeamId;
    private long bowlingTeamId;
    private int totalScore;
    private int totalWicket;
    private int totalOver;
    private long matchId;
}
