package com.rewards.reward_api.controller;

import com.rewards.reward_api.dto.RewardResponse;
import com.rewards.reward_api.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/rewards")
public class RewardController{

    @Autowired
    private RewardService rewardService;

    /**
     * Calculates reward points for a customer within a given date range
     *
     * @param customerId Customer ID to calculate rewards for
     * @param from Start date (inclusive)
     * @param to End date (inclusive)
     *
     * @return Reward details including points per month and total
     */

    @GetMapping
    public ResponseEntity<RewardResponse> getCustomerRewards(
            @RequestParam String customerId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
            ){
        RewardResponse response = rewardService.calculateRewards(customerId, from, to);
        return ResponseEntity.ok(response);
    }
}
