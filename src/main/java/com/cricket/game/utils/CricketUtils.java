package com.cricket.game.utils;

import java.util.Random;

public class CricketUtils {


    public  static String getRandomScores(){

        String[] scoreOrWicket={"0","1","2","3","4","5","6","w"};
        Random random=new Random();
        int index = random.nextInt(scoreOrWicket.length);
        String result = scoreOrWicket[index];
        return result;

    }
}
