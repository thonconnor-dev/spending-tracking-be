package com.connordev.spending_tracking_be.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connordev.spending_tracking_be.entities.TransactionEntity;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {

}
