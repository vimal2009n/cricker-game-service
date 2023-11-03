package com.cricket.game.service.serviceImpl;

import com.cricket.game.entity.Team;
import com.cricket.game.entity.TeamEntity;
import com.cricket.game.mapper.TeamMapper;
import com.cricket.game.model.TeamMapperModel;
import com.cricket.game.model.TeamModel;
import com.cricket.game.repository.TeamRepository;
import com.cricket.game.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public TeamMapperModel saveTeam(TeamMapperModel model) {

        TeamMapperModel responseModel=new TeamMapperModel();
        Team response ;
        boolean value = validateTeamName(model);
        if(value) {
            Team teamEntity = teamMapper.mapTeamModelToTeamEntity(model);

            Team newTeam = new Team();
            newTeam.setTeamName(teamEntity.getTeamName());
            response = teamRepository.save(newTeam);

             responseModel = teamMapper.mapTeamEntityToTeamModel(response);
        }

        return responseModel;
    }

    @Override
    public TeamMapperModel updateTeam(TeamMapperModel model) {

        Team response = null;
        Team teamEntity = teamMapper.mapTeamModelToTeamEntity(model);
        Optional<Team> existTeam = teamRepository.findById(teamEntity.getId());
        if(existTeam.isPresent()){

            existTeam.get().setTeamName(teamEntity.getTeamName());
            response=teamRepository.save(existTeam.get());
        }
        TeamMapperModel responseModel = teamMapper.mapTeamEntityToTeamModel(response);
        return responseModel;
    }

    @Override
    public List<TeamMapperModel> getAllTeam() {

        List<Team> existingTeamList = teamRepository.findAll();
        List<TeamMapperModel> teamList=new ArrayList<>();
        if(!existingTeamList.isEmpty()) {
             teamList = existingTeamList.stream()
                    .map(m -> teamMapper.mapTeamEntityToTeamModel(m))
                    .collect(Collectors.toList());
        }
        return teamList;
    }

    @Override
    public void removeTeamById(long teamId) {

        teamRepository.deleteById(teamId);
    }


    private boolean validateTeamName(TeamMapperModel teamModel) {

        if (teamModel.getTeamName() != null) {

            List<Team> teamList = teamRepository.findByTeamName(teamModel.getTeamName());

            if (!teamList.isEmpty()) {

                return false;
            }
        }
      return  true;
    }
}
