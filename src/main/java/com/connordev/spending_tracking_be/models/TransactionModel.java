package com.connordev.spending_tracking_be.models;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionModel {
    private String id;
    private String name;
    private String amount;
    private CategoryModel category;
    private String transactionType;
    private LocalDateTime createdDate;
}
