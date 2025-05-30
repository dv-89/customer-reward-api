package com.rewards.reward_api.service;

import com.rewards.reward_api.dto.RewardResponse;
import com.rewards.reward_api.exception.CustomerNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for RewardService class
 *
 * These tests verify the behavior of the reward calculation logic under various scenarios,
 *  including:
 *
 *      Successful reward calculation for a valid customer.
 *      Handling of unknown customers.
 *      Scenarios with no eligible transactions.
 */
@SpringBootTest
public class RewardServiceTest {

    private final RewardService rewardService = new RewardService();

    /**
     * Test for reward calculation when a valid customer ID is provided.
     * Expects a valid RewardResponse containing:
     *
     *     Correct customer details
     *     Total reward points
     *     Transaction count
     *     Monthly breakdown presence
     *
     *
     * @throws Exception if future.get() is interrupted or fails
     */
    @Test
    void testCalculateRewardsValidCustomer() throws Exception {
        String customerId = "1";
        LocalDate from = LocalDate.of(2024, 1, 1);
        LocalDate to = LocalDate.of(2024, 3, 31);

        CompletableFuture<RewardResponse> future = rewardService.calculateRewards(customerId, from, to);
        RewardResponse response = future.get();

        assertEquals("1", response.getCustomerId());
        assertEquals("Dinesh", response.getCustomerName());
        assertEquals(365, response.getTotalPoints());
        assertEquals(3, response.getTransactionCount());
        assertTrue(response.getMonthlyBreakdown().containsKey("January"));
    }

    /**
     * Test for behavior when a non-existent customer ID is passed.
     * Expects a CustomerNotFoundException to be thrown.
     */
    @Test
    void testCalculateRewardsCustomerNotFound() {
        String customerId = "999";
        LocalDate from = LocalDate.of(2024, 1, 1);
        LocalDate to = LocalDate.of(2024, 3, 31);

        Exception exception = assertThrows(CustomerNotFoundException.class, () ->
                rewardService.calculateRewards(customerId, from, to).join()
        );

        assertTrue(exception.getMessage().contains("not found"));
    }

    /**
     * Test for reward calculation when a customer has no eligible transactions in the given time frame.
     * Expects zero total points and zero transaction count.
     *
     * @throws Exception if future.get() is interrupted or fails
     */
    @Test
    void testCalculateRewardsEmptyTransaction() throws Exception {
        String customerId = "4";
        LocalDate from = LocalDate.of(2024, 1, 1);
        LocalDate to = LocalDate.of(2024, 2, 28);

        RewardResponse response = rewardService.calculateRewards(customerId, from, to).get();

        assertEquals(0, response.getTotalPoints());
        assertEquals(0, response.getTransactionCount());
    }
}

