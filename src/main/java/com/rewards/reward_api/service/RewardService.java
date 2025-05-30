package com.rewards.reward_api.service;


import com.rewards.reward_api.dto.RewardResponse;
import com.rewards.reward_api.exception.CustomerNotFoundException;
import com.rewards.reward_api.model.Customer;
import com.rewards.reward_api.model.Transaction;
import com.rewards.reward_api.util.RewardCalculatorUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;

/**
 * Service class responsible for calculating reward points for customers.
 * Filters transacions by customer and date range, calculates points, and builds a response.
 */

@Service
public class RewardService {

    /**
     * Mock Transaction Data
     *
     * Note: 50 to 100  - 1 point for each dollar
     *             100+ - 2 points for each dollar
     */
    private final List<Transaction> transactions = List.of(
        new Transaction("1", "T001", LocalDate.of(2024, 1, 10), 120),
        new Transaction("1", "T002", LocalDate.of(2024, 2, 5), 75),
        new Transaction("1", "T003", LocalDate.of(2024, 3, 15), 200),
        new Transaction("2", "T004", LocalDate.of(2024, 1, 20), 55),
        new Transaction("2", "T005", LocalDate.of(2024, 2, 8), 150),
        new Transaction("3", "T006", LocalDate.of(2024, 1, 10), 90),
        new Transaction("3", "T007", LocalDate.of(2024, 3, 29), 20)
    );


    /**
     * Mock customer data mapped by customer ID
     */
    private final Map<String, Customer> customerMap = Map.of(
            "1", new Customer("1", "Dinesh", "dinesh@test.com"),
            "2", new Customer("2", "Sai ram", "sr@test.com"),
            "3", new Customer("3", "Sumith", "sg@test.com")
    );

    /**
     * Calculates reward points earned by a specific customer within a given date range.
     *
     * @param customerId    - Id of customer
     * @param from          - start date of range (inclusive)
     * @param to            - end date of range (inclusive)
     * @return              - A RewardResponse object containing the customers reward details
     */

    @Async
    public CompletableFuture<RewardResponse> calculateRewards(String customerId, LocalDate from, LocalDate to){

        // get customer details
        Customer customer = customerMap.get(customerId);

        // check whether a customer with that id exists or not.
        if(customer == null){
            throw new CustomerNotFoundException("customer with ID " + customerId + " not found" );
        }

        // filter transactions by customer Id and date range

        List<Transaction> filtered_transactions = transactions.stream()
                .filter(t -> t.getCustomerId().equals(customerId))
                .filter(t -> !t.getDate().isBefore(from) && !t.getDate().isAfter(to))
                .toList();

        // map to hold reward points for each month
        Map<String, Integer> monthlyPoints = new LinkedHashMap<>();

        int totalPoints = 0;

        for(Transaction t: filtered_transactions){
            int points = RewardCalculatorUtil.calculatePoints(t.getAmount());

            // group by month name

            String month = t.getDate().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            monthlyPoints.put(month, monthlyPoints.getOrDefault(month, 0) + points);

            totalPoints += points;
        }


        // Build response DTO
        RewardResponse response = new RewardResponse(
                                                        customer.getId(),
                                                        customer.getName(),
                                                        totalPoints,
                                                        filtered_transactions.size(),
                                                        from,
                                                        to,
                                                        monthlyPoints
        );

        return CompletableFuture.completedFuture(response);
    }
}
