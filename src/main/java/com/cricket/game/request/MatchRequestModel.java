package com.cricket.game.request;

import lombok.Data;



@Data
public class MatchRequestModel {

    private long id;
    private long teamAId;
    private long teamBId;
    private int totalOver;
    private String venue;
}
