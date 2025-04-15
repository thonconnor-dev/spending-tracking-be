package com.connordev.spending_tracking_be.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "balance_history")
public class BalanceHistoryEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "balance")
    private Float balance;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "transaction_id")
    private String transactionId;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Float getBalance() {
        return balance;
    }
    public void setBalance(Float balance) {
        this.balance = balance;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    

}
