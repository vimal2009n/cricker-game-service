package com.cricket.game.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;



@Data
public class TeamMapperModel {

    private Long id;
    @Valid
    @NotNull(message = "team name is required")
    private String teamName;

}
