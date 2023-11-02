package com.cricket.game.service;

import com.cricket.game.model.PlayersMapperModel;

import java.util.List;

public interface PlayersService {

    PlayersMapperModel savePlayer(PlayersMapperModel model);

    List<PlayersMapperModel> getAllPlayer();

    List<PlayersMapperModel> getPlayersByTeamId(long id);

    void deletePlayerByID(long id);

}
