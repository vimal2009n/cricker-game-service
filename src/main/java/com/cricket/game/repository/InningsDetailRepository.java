package com.cricket.game.repository;

import com.cricket.game.entity.InningsDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InningsDetailRepository extends JpaRepository<InningsDetailsEntity,Long> {

    List<InningsDetailsEntity> findByMatchIdId(long matchId);

    InningsDetailsEntity findByMatchIdIdAndBattingTeamId(long matchId,long teamId);

}
