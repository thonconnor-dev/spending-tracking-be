package com.connordev.spending_tracking_be.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connordev.spending_tracking_be.entities.BalanceHistoryEntity;

@Repository
public interface BalanceHistoryRepository extends JpaRepository<BalanceHistoryEntity, String> {

}
