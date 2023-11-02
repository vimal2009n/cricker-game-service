package com.cricket.game.model;

import lombok.Data;

@Data
public class BallTrackingMapperModel {

    private long id;
    private long batsManId;
    private long bowlerId;
    private int four;
    private int six;
    private int wicket;
    private long inningsId;
    private long inningsPerOverId;
}
