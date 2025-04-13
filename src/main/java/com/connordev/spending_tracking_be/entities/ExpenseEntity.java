package com.connordev.spending_tracking_be.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "expense")
public class ExpenseEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "transaction_id")
    private String transactionId;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    
}
