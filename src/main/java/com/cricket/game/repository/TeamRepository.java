package com.cricket.game.repository;

import com.cricket.game.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {


        List<Team> findByTeamName(String teamName);
}
