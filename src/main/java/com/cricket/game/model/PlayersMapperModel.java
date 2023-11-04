package com.cricket.game.model;



import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PlayersMapperModel {


    private Long id;
    @Valid
    @NotBlank(message = "player name is required")
    private String playerName;
    @Valid
    @NotBlank(message = "team Id is required")
    private long teamId;
}
