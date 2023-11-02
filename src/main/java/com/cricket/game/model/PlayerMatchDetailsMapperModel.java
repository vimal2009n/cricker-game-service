package com.cricket.game.model;

import lombok.Data;

@Data
public class PlayerMatchDetailsMapperModel {

    private long id;
    private long runScored;
    private long takenWicket;
    private long totalOverBowled;
    private long matchId;
    private long playerId;
}
