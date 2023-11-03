package com.cricket.game.repository;

import com.cricket.game.entity.PlayerMatchDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerMatchDetailsRepository extends JpaRepository<PlayerMatchDetailsEntity,Long> {

    List<PlayerMatchDetailsEntity> findByMatchIdId(long id);
}
