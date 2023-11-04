package com.cricket.game.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "ball_tracking")
@Entity
@Data
public class BallTrackingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name ="batsman_id" ,nullable = false)
    private long batsManId;

    @Column(name ="bowler_id",nullable = false )
    private long bowlerId;

    @Column(name ="four" )
    private int four;

    @Column(name ="six" )
    private int six;

    @Column(name ="wicket" )
    private int wicket;

    @Column(name ="run" )
    private int run;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "innings_id",nullable = false)
    private InningsDetailsEntity inningsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "innings_over_id",nullable = false)
    private InningsPerOverEntity inningsPerOverId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id",nullable = false)
    private MatchEntity matchId;
}
