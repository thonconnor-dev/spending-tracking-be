package com.connordev.spending_tracking_be.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class CashflowTransactionId {
    @Column(name = "cashflow_id")
    private String cashflowId;
    @Column(name = "transaction_id")
    private String transactionId;
    public String getCashflowId() {
        return cashflowId;
    }
    public void setCashflowId(String cashflowId) {
        this.cashflowId = cashflowId;
    }
    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
