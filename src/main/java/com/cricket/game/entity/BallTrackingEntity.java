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

   /* @Column(name ="innings_id" )
    private long innigsId;

    @Column(name ="innings_over_id" )
    private long innigsOverId;*/


    @Column(name ="batsman_id" )
    private long batsManId;

    @Column(name ="bowler_id" )
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
    @JoinColumn(name = "innings_id")
    private InningsDetailsEntity inningsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "innings_over_id")
    private InningsPerOverEntity inningsPerOverId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private MatchEntity matchId;
}
