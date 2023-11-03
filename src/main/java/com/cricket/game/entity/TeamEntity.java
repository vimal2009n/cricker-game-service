package com.cricket.game.entity;

import lombok.Data;

import java.util.List;

@Data
public class TeamEntity {

    private long id;
    private String teamName;
    List<PlayerEntity> players;
    private long totalScore;
    private int totalWicket;
    private int totalOver;
}
