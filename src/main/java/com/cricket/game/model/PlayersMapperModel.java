package com.cricket.game.model;

import com.cricket.game.entity.Team;
import lombok.Data;

@Data
public class PlayersMapperModel {

    private Long id;
    private String playerName;
    private long teamId;
}
