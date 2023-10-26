package com.cricket.game.entity;

import lombok.Data;

import java.util.List;

@Data
public class TeamEntity {

    private int id;
    private String teamName;
    List<PlayerEntity> players;
    private long totalScore;
    private int totalWicket;
    private int totalOver;
}
