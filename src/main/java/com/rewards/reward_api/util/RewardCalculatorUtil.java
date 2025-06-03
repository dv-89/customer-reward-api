package com.rewards.reward_api.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Calculates reward points based on the transaction amount.
 */
public class RewardCalculatorUtil {
    public static int calculatePoints(BigDecimal amount){

        if(amount == null){
            return 0;
        }

        BigDecimal roundedAmount = amount.setScale(0, RoundingMode.DOWN);
        BigDecimal fifty = new BigDecimal("50");
        BigDecimal hundred = new BigDecimal("100");

        int points = 0;


        if(roundedAmount.compareTo(hundred) > 0){
            // find no of dollars above 100 and multiply with
            // number of points (as per doc it is 2)
            BigDecimal aboveHundred = roundedAmount.subtract(hundred).multiply(new BigDecimal("2"));
            points += aboveHundred.intValue() + 50;

        } else if(roundedAmount.compareTo(fifty) > 0){
            // same as above
            // but we add 1 point for every dollar spent btw 50 and 100
            BigDecimal aboveFifty = roundedAmount.subtract(fifty);
            points += aboveFifty.intValue();
        }
        return points;
    }
}
