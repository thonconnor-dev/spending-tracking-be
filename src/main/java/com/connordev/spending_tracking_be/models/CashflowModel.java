package com.connordev.spending_tracking_be.models;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CashflowModel {
    private String id;
    private Float incomeAmount;
    private Float expenseAmount;
    private int month;
    private int year;
    private List<TransactionModel> transactions;
}
