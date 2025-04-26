package com.connordev.spending_tracking_be.models;

import java.time.LocalDateTime;

import com.connordev.spending_tracking_be.enums.TransactionType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionModel {
    private String id;
    private String name;
    private String amount;
    private CategoryModel category;
    private TransactionType transactionType;
    private LocalDateTime createdDate;
}
