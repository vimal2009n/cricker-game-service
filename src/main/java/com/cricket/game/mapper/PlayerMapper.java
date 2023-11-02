package com.cricket.game.mapper;

import com.cricket.game.entity.PlayerEntity;
import com.cricket.game.entity.Players;
import com.cricket.game.model.PlayersMapperModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface PlayerMapper {


    @Mapping(target="team.id",source = "teamId")
    Players mapPlayerModelToPlayerEntity(PlayersMapperModel model);
    @Mapping(target="teamId",source = "team.id")
    PlayersMapperModel mapPlayerEntityToPlayerModel(Players entity);

}
