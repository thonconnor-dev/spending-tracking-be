package com.connordev.spending_tracking_be.entities;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity(name = "cashflow_transaction")
public class CashflowTransactionEntity {
    @EmbeddedId
    private CashflowTransactionId cashflowTransactionId;

    public CashflowTransactionId getCashflowTransactionId() {
        return cashflowTransactionId;
    }

    public void setCashflowTransactionId(CashflowTransactionId cashflowTransactionId) {
        this.cashflowTransactionId = cashflowTransactionId;
    }

}
