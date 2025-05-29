package com.rewards.reward_api.model;

import java.time.LocalDate;

/**
 * Represents a customer's transaction for reward point calculation
 */
public class Transaction{

    /** unique identifier of customer who made this transaction **/
    private String customerId;


    /** unique identifier for transaction **/
    private String transactitonId;


    /** Date when the transaction was made **/
    private LocalDate date;

    /** Transaction amount **/
    private int amount;

    // ----- constructors ---

    /**
     * Default Constructor
     */
    public Transaction(){

    }

    /**
     *
     * All Args Constructor that constucts a transaction with specified values
     *
     * @param customerId        - customer ID
     * @param transactitonId    - transaction ID
     * @param date              - transaction date
     * @param amount            - transaction amount
     */
    public Transaction(String customerId, String transactitonId, LocalDate date, int amount) {
        this.customerId = customerId;
        this.transactitonId = transactitonId;
        this.date = date;
        this.amount = amount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTransactitonId() {
        return transactitonId;
    }

    public void setTransactitonId(String transactitonId) {
        this.transactitonId = transactitonId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
