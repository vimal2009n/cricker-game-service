package com.cricket.game.utils;

import com.cricket.game.constants.CricketConstants;
import com.cricket.game.entity.PlayerEntity;
import com.cricket.game.entity.Players;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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




    public static List<Integer> getBallByOver(int totalOver){

        List<Integer> totalBallByOver=new ArrayList<>();
        for(int i=1;i<=totalOver;i++){

            totalBallByOver.add(i*6);

        }
        return totalBallByOver;
    }


    public static long getPlayerFromList(List<Long> playerList){

        long currentId=0;
        if(!playerList.isEmpty()){
             currentId = playerList.get(0);
        }

        return currentId;
    }

    public static void removePlayerFromList(List<Long> playerList,long currentId){

        if(!playerList.isEmpty()){
            playerList.remove(currentId);
        }
    }

    public static List<Integer>  getMultipleOfSix(int totalOver){

        List<Integer> multiple=new ArrayList<>();
        for(int i=1;i<=totalOver;i++){

            multiple.add(i*6);
        }
        return multiple;
    }

    public  static String getCurrentDate(){

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(CricketConstants.DATE_FORMAT);
        String strDate = formatter.format(date);
        return strDate;
    }

}
