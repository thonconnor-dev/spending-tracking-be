package com.connordev.spending_tracking_be.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connordev.spending_tracking_be.entities.ExpenseEntity;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, String> {

}
