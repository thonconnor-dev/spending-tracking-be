package com.connordev.spending_tracking_be.enums;

public enum TransactionType {
    INCOME("income"),
    EXPENSE("expense");
    private String type;

    private TransactionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static TransactionType getTypeFromString(String type) {
        for (TransactionType transactionType : TransactionType.values()) {
            if (transactionType.getType().equalsIgnoreCase(type)) {
                return transactionType;
            }
        }
        return null;
    }

}
