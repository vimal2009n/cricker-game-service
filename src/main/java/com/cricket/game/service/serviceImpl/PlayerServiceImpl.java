package com.cricket.game.service.serviceImpl;

import com.cricket.game.entity.PlayerEntity;
import com.cricket.game.entity.Players;
import com.cricket.game.mapper.PlayerMapper;
import com.cricket.game.model.PlayersMapperModel;
import com.cricket.game.repository.PlayerRepository;
import com.cricket.game.service.PlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayersService {

    @Autowired
    private PlayerMapper playerMapper;

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public PlayersMapperModel savePlayer(PlayersMapperModel model) {

        Players response = null;
        Players playerEntity = playerMapper.mapPlayerModelToPlayerEntity(model);

        if(playerEntity.getId()==null){

            Players newPlayer=new Players();
            newPlayer.setPlayerName(playerEntity.getPlayerName());
            newPlayer.setTeam(playerEntity.getTeam());
            response=playerRepository.save(newPlayer);
        }else {

            Optional<Players> existPlayer = playerRepository.findById(playerEntity.getId());
            if(existPlayer.isPresent()){

                existPlayer.get().setPlayerName(playerEntity.getPlayerName());
                existPlayer.get().setTeam(playerEntity.getTeam());
                response=playerRepository.save(existPlayer.get());
            }
        }

        PlayersMapperModel responseModel = playerMapper.mapPlayerEntityToPlayerModel(response);

        return responseModel;
    }

    @Override
    public List<PlayersMapperModel> getAllPlayer() {

        List<Players> response = playerRepository.findAll();
        List<PlayersMapperModel> responseList = response.stream().map(m -> playerMapper.mapPlayerEntityToPlayerModel(m)).collect(Collectors.toList());
        return responseList;
    }

    @Override
    public List<PlayersMapperModel> getPlayersByTeamId(long id) {

        List<PlayersMapperModel> response=new ArrayList<>();
        List<Players> playersList = playerRepository.findByTeamId(id);
        if(!playersList.isEmpty()){

             response = playersList.stream().map(m -> playerMapper.mapPlayerEntityToPlayerModel(m))
                     .collect(Collectors.toList());
        }

        return response;
    }

    @Override
    public void deletePlayerByID(long id) {

        playerRepository.deleteById(id);
    }
}
