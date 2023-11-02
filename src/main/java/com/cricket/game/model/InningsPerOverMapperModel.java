package com.cricket.game.model;

import com.cricket.game.entity.InningsDetailsEntity;
import com.cricket.game.entity.MatchEntity;
import lombok.Data;

@Data
public class InningsPerOverMapperModel {

    private long id;
    private int totalRunTaken;
    private long bowlerId;
    private int extras;
    private long matchId;
    private long inningsId;
}
