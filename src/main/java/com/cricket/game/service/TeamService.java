package com.cricket.game.service;

import com.cricket.game.model.TeamMapperModel;
import com.cricket.game.model.TeamModel;

import java.util.List;

public interface TeamService {

    TeamMapperModel saveTeam(TeamMapperModel model);

    List<TeamMapperModel>  getAllTeam();

    void removeTeamById(long teamId);

}
