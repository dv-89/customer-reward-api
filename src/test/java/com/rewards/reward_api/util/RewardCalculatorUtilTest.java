package com.rewards.reward_api.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for sample cases like
 *
 * 1. if amount > 100
 * 2. if amount > 50
 * 3. if amount < 50
 *
 */
public class RewardCalculatorUtilTest {

    @Test
    public void testRewardForAmountGreaterthan100Dollars(){
        BigDecimal amount = new BigDecimal("120.50");

        int expectedPoints = 90;
        int actualPoints = RewardCalculatorUtil.calculatePoints(amount);
        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void testRewardsForAmountBelow50Dollars(){
        BigDecimal amount = new BigDecimal("45.50");

        int expectedPoints = 0;
        int actualPoints = RewardCalculatorUtil.calculatePoints(amount);
        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void testRewardsForAmountInBtw50and100Dollars(){
        BigDecimal amount = new BigDecimal("75.25");

        int expectedPoints = 25;
        int actualPoints = RewardCalculatorUtil.calculatePoints(amount);
        assertEquals(expectedPoints, actualPoints);
    }
}
