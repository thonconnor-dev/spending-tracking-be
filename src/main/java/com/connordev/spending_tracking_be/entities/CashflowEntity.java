package com.connordev.spending_tracking_be.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "cashflow")
public class CashflowEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "cashflow_month")
    private int month;
    @Column(name = "cashflow_year")
    private int year;
    @Column(name = "income_amount")
    private Double incomeAmount;
    @Column(name = "expense_amount")
    private Double expenseAmount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Double getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(Double incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public Double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(Double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

}
