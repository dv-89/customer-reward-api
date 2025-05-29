package com.rewards.reward_api.util;

public class RewardCalculatorUtil {
    public static int calculatePoints(int amount){
        int points = 0;
        if(amount > 100){
            // find no of dollars above 100 and multiply with
            // number of points (as per doc it is 2)
            points += (amount - 100) * 2 + 50;
        } else if(amount > 50){
            // same as above
            // but we add 1 point for every dollar spent btw 50 and 100
            points += (amount - 50);
        }
        return points;
    }
}
