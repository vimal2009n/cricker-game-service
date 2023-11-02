package com.cricket.game.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "innings_details")
@Data
public class InningsDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "innings_number")
    private int inningsNumber;

    @Column(name = "batting_team_id")
    private long battingTeamId;

    @Column(name = "bowling_team_id")
    private long bowlingTeamId;

    @Column(name = "total_score")
    private int totalScore;

    @Column(name = "total_wicket")
    private int totalWicket;

    @Column(name = "total_over")
    private int totalOver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matchId")
    private MatchEntity matchId;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "inningsId")
    private List<InningsPerOverEntity> inningsPerOvers;


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "inningsId")
    private List<BallTrackingEntity> ballTracking;


}
