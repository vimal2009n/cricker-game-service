package com.cricket.game.mapper;

import com.cricket.game.entity.MatchEntity;
import com.cricket.game.model.MatchMapperModel;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface MatchMapper {

    MatchEntity mapMatchModelToMatchEntity(MatchMapperModel model);
    MatchMapperModel mapMatchEntityToMatchModel(MatchEntity entity);



}
