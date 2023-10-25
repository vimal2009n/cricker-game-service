package com.cricket.game.model;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class TeamScoreModel {

    private int id;
    private String teamName;
    private long totalScore;
    private int totalWicket;
    private int totalOver;
    private HashMap<String,Integer> batsmanLIst;

}
