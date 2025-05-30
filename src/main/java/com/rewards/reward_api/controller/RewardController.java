package com.rewards.reward_api.controller;

import com.rewards.reward_api.dto.RewardResponse;
import com.rewards.reward_api.service.RewardService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/rewards")
@Validated
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
    public CompletableFuture<ResponseEntity<RewardResponse>> getCustomerRewards(
            @RequestParam @NotBlank String customerId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @NotNull LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @NotNull LocalDate to
            ){

        if(from.isAfter(to)){
            throw new IllegalArgumentException("Start date must be before or equal to end date.");
        }

        return rewardService.calculateRewards(customerId, from, to).thenApply(ResponseEntity::ok);
    }
}
