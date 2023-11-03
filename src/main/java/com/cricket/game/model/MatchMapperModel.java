package com.cricket.game.model;

import lombok.Data;

@Data
public class MatchMapperModel {

    private long id;
    private long teamAId;
    private long teamBId;
    private String matchDate;
    private long winnerTeamId;
    private String venue;
    private String matchSummary;

    private int totalOver;
}
