package com.cricket.game.response;

import lombok.Data;

@Data
public class ScoreBoardResponseModel {


    private TeamResponseModel teamA;
    private TeamResponseModel teamB;

    private String result;
}
