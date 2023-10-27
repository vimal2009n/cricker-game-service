package com.cricket.game.model;

import com.cricket.game.entity.PlayerEntity;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class TeamModel {

    private int id;
    private String teamName;
    List<PlayerEntity> players;
    private long totalScore;
    private int totalWicket;
    private int totalOver;

    private HashMap<String,Integer> batsmanLIst;

    List<String>  bowlersList;
    private String battingTeam;
}
