package com.cricket.game.entity;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Table(name = "innings_per_over")
@Entity
@Data
public class InningsPerOverEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "total_run_taken")
    private int totalRunTaken;

    @Column(name = "bowler_id")
    private long bowlerId;

    @Column(name = "extras")
    private int extras;

    @Column(name = "wicket_per_over")
    private int wicketPerOver;

    @Column(name = "innings_number")
    private int inningsNumber;

    @Column(name = "over_deatails")
    private int numberOfOver;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private MatchEntity matchId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "innings_id")
    private InningsDetailsEntity inningsId;


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "inningsPerOverId")
    private List<BallTrackingEntity> ballTrackingEntity;

}
