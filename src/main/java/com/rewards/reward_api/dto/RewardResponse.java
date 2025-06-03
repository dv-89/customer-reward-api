package com.rewards.reward_api.dto;

import com.rewards.reward_api.model.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * DTO representing the reward summary for a customer.
 */
public class RewardResponse {

    /** Unique identifier of the customer */
    private String customerId;

    /** Name of customer */
    private String customerName;

    /** Total reward points accumulated by customer */
    private int totalPoints;

    /** List of transactions processed for calculation */
    private List<Transaction> transactionList;

    /** Start date (inclusive) of the period for which rewards were calculated */
    private LocalDate fromDate;

    /** End date of the period for which rewards were calculated */
    private LocalDate toDate;

    /** Monthly breakdown of reward points */
    private Map<String, Integer> monthlyBreakdown;


    // --------- Constructors -----------

    /**
     * Default Constructor
     */
    public RewardResponse(){

    }

    /**
     * Constructor that constructs a RewardResponse with default values.
     *
     * @param customerId            - customer ID
     * @param customerName          - customer Name
     * @param totalPoints           - total reward points
     * @param transactionList       - list of transactions
     * @param fromDate              - start date of reward period
     * @param toDate                - end date of reward period
     * @param monthlyBreakdown      - map that has reward points for each month
     */
    public RewardResponse(String customerId, String customerName, int totalPoints,
                          List<Transaction> transactionList, LocalDate fromDate, LocalDate toDate,
                          Map<String, Integer> monthlyBreakdown){
        this.customerId = customerId;
        this.customerName = customerName;
        this.totalPoints = totalPoints;
        this.transactionList = transactionList;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.monthlyBreakdown = monthlyBreakdown;
    }

    // --------- Getters & Setters ------------
    public String getCustomerId(){
        return customerId;
    }


    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public Map<String, Integer> getMonthlyBreakdown() {
        return monthlyBreakdown;
    }

    public void setMonthlyBreakdown(Map<String, Integer> monthlyBreakdown) {
        this.monthlyBreakdown = monthlyBreakdown;
    }

    public int getTransactionCount() {
        return (transactionList == null)? 0 : transactionList.size();
    }
}
