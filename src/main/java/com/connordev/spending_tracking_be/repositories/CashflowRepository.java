package com.connordev.spending_tracking_be.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connordev.spending_tracking_be.entities.CashflowEntity;

@Repository
public interface CashflowRepository extends JpaRepository<CashflowEntity, String> {
    Optional<CashflowEntity> findByMonthAndYear(int month, int year);
}
