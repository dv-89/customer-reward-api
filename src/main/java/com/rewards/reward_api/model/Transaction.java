package com.rewards.reward_api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents a customer's transaction for reward point calculation
 */
public class Transaction{

    /** unique identifier of customer who made this transaction **/
    private String customerId;


    /** unique identifier for transaction **/
    private String transactionId;


    /** Date when the transaction was made **/
    private LocalDate date;

    /** Transaction amount **/
    private BigDecimal amount;

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
     * @param transactionId    - transaction ID
     * @param date              - transaction date
     * @param amount            - transaction amount
     */
    public Transaction(String customerId, String transactionId, LocalDate date, BigDecimal amount) {
        this.customerId = customerId;
        this.transactionId = transactionId;
        this.date = date;
        this.amount = amount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
