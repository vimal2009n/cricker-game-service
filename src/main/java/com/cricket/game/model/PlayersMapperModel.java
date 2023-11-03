package com.cricket.game.model;



import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PlayersMapperModel {

    @NotBlank(message = "Id is required")
    private Long id;
    @NotBlank(message = "Id is required")
    private String playerName;
    private long teamId;
}
