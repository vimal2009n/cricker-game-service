package com.cricket.game.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "player_match_details")
@Entity
@Data
public class PlayerMatchDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "run_scored")
    private long runScored;


    @Column(name = "wicket_takened")
    private long takenWicket;

    @Column(name = "total_over_bowled")
    private long totalOverBowled;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private MatchEntity matchId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Players playerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
}
