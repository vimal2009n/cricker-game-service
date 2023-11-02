package com.cricket.game.mapper;

import com.cricket.game.entity.Team;
import com.cricket.game.model.TeamMapperModel;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface TeamMapper {


    Team mapTeamModelToTeamEntity(TeamMapperModel model);
    TeamMapperModel mapTeamEntityToTeamModel(Team entity);
}
