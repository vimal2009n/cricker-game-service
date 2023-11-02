package com.cricket.game.repository;

import com.cricket.game.entity.Players;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Players,Long> {

    List<Players> findByTeamId(long id);
}
