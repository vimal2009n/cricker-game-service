package com.cricket.game.response;

import lombok.Data;

@Data
public class PlayerDetailByMatchResponseModel {

    private PlayerResponseModel teamAPlayers;
    private PlayerResponseModel teamBPlayers;
}
