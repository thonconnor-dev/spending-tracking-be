package com.connordev.spending_tracking_be.models;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BalanceHistoryModel {
    private String id;
    private Double balance;
    private LocalDateTime createdDate;
    private TransactionModel transaction;
}
