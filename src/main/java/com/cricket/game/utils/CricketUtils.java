package com.cricket.game.utils;

import com.cricket.game.entity.PlayerEntity;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CricketUtils {


    public  static String getRandomScores(){

        String[] scoreOrWicket={"0","1","2","3","4","5","6","w"};
        Random random=new Random();
        int index = random.nextInt(scoreOrWicket.length);
        String result = scoreOrWicket[index];
        return result;

    }

    public static String getRandomBatsMan(List<PlayerEntity>  list,int wicket){


        int batsmanCount = list.size();
        List<String> batsManList = list.stream().map(f -> f.getName()).collect(Collectors.toList());

        wicket=wicket-1;
        if(wicket<batsmanCount) {
            String batsman = batsManList.get(wicket);
            batsManList.remove(batsman);
            return batsman;
        }

        return null;

    }
}
