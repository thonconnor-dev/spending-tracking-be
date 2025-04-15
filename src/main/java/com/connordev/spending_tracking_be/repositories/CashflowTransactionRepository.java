package com.connordev.spending_tracking_be.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.connordev.spending_tracking_be.entities.CashflowTransactionEntity;
import com.connordev.spending_tracking_be.entities.CashflowTransactionId;

@Repository
public interface CashflowTransactionRepository extends JpaRepository<CashflowTransactionEntity, CashflowTransactionId> {

}
