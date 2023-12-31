package com.cricket.game.constants;

public class CricketApiConstants {



    // ********************  Team **************
    public static final String TEAM_API="/api/team/";
    public static final String SAVE_TEAM="/saveTeam";
    public static final String GET_ALL_TEAM="/getAllTeam";
    public static final String UPDATE_TEAM="/updateTeam";
    public static final String DELETE_TEAM_BY_ID="/deleteTeamById";


    //******************* Player *****************
    public static final String PLAYER_API="/api/player/";
    public static final String SAVE_PLAYER="/savePlayer";
    public static final String UPDATE_PLAYER="/updatePlayer";
    public static final String GET_ALL_PLAYER="/getAllPlayer";
    public static final String GET_PLAYER_BY_TEAM_ID="/getPlayerByTeamId";
    public static final String DELETE_PLAYER_BY_ID="/deletePlayerById";

    //**************** Match *****************

    public static final String CRICKET_API="/api/match/";
    public static final String GET_MATCH_RESULT="/getMatchResult";

    public static final String PLAY_MATCH="/playMatch";
    public static final String GET_MATCH_DETAILS_BY_MATCH_ID="/getMatchDetailsByMatchId";

    public static final String GET_PLAYER_DETAILS_BY_MATCH_ID="/getPlayerDetailsByMatchId";


}
