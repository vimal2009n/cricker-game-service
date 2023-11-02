package com.cricket.game.repository;

import com.cricket.game.entity.InningsPerOverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InningsPerOverRepository extends JpaRepository<InningsPerOverEntity,Long> {


    List<InningsPerOverEntity>  findByMatchIdIdAndInningsIdId(long matchId,long inningsId);

}
