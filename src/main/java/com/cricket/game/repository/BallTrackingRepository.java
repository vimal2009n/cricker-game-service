package com.cricket.game.repository;

import com.cricket.game.entity.BallTrackingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BallTrackingRepository extends JpaRepository<BallTrackingEntity,Long> {

    List<BallTrackingEntity> findByInningsPerOverIdId(long id);

    List<BallTrackingEntity> findByInningsIdIdAndInningsPerOverIdId(long innings,long inningsPerOver);

    List<BallTrackingEntity> findByMatchIdIdAndBatsManId(long matchId,long batsManId);
    List<BallTrackingEntity> findByMatchIdIdAndBowlerId(long matchId,long bowler);
}
