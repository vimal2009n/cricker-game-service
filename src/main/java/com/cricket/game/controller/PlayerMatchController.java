package com.cricket.game.controller;


import com.cricket.game.service.PlayerMatchDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/matchPlayer")
public class PlayerMatchController {

    @Autowired
    private PlayerMatchDetailsService playerMatchDetailsService;

    @GetMapping("/generateMatchPlayerDetails")
    public void generateMatchPlayerDetails(){

        playerMatchDetailsService.generatePlayerDetailsByMatch();
    }


}
