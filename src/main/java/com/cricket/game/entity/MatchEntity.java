package com.cricket.game.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "match_tbl")
@Data
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "teamA_id")
    private long teamAId;


    @Column(name = "teamB_id")
    private long teamBId;

    @Column(name = "match_date")
    private String matchDate;

    @Column(name = "winner_team_id")
    private long winnerTeamId;

    @Column(name = "venue")
    private String venue;

    @Column(name = "match_summury")
    private String matchSummary;



    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "matchId")
    private List<InningsDetailsEntity> inningsDetails;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "matchId")
    private List<InningsPerOverEntity> inningsPerOvers;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "matchId")
    private List<PlayerMatchDetailsEntity> playerMatchDetails;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "matchId")
    private List<BallTrackingEntity> ballTrackingEntities;


}
