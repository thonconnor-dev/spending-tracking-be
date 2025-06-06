package com.connordev.spending_tracking_be.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.connordev.spending_tracking_be.entities.CashflowEntity;
import com.connordev.spending_tracking_be.entities.CashflowTransactionEntity;
import com.connordev.spending_tracking_be.entities.CashflowTransactionId;
import com.connordev.spending_tracking_be.mappers.CashflowMapper;
import com.connordev.spending_tracking_be.models.CashflowModel;
import com.connordev.spending_tracking_be.repositories.CashflowRepository;
import com.connordev.spending_tracking_be.repositories.CashflowTransactionRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CashflowService {
    private CashflowRepository cashflowRepository;
    private CashflowTransactionRepository cashflowTransactionRepository;
    private final CashflowMapper cashflowMapper;

    public CashflowService(CashflowRepository cashflowRepository, CashflowMapper cashflowMapper,
            CashflowTransactionRepository cashflowTransactionRepository) {
        this.cashflowTransactionRepository = cashflowTransactionRepository;
        this.cashflowMapper = cashflowMapper;
        this.cashflowRepository = cashflowRepository;
    }

    @Transactional
    public CashflowModel createNewCashflow(String transactionId, int month, int year, double incomeAmount,
            double expenseAmount) {
        log.info("Creating new cashflow for month: {}, year: {}, income: {}, expense: {}", month, year, incomeAmount,
                expenseAmount);

        CashflowModel cashflowModel = CashflowModel.builder()
                .month(month)
                .year(year)
                .incomeAmount(incomeAmount)
                .expenseAmount(expenseAmount)
                .build();

        CashflowEntity cashflowEntity = cashflowMapper.mapToEntity(cashflowModel);

        // Save the cashflow model to the repository
        cashflowRepository.save(cashflowEntity);
        cashflowModel.setId(cashflowEntity.getId());

        log.info("Created new cashflow: {}", cashflowModel);
        // Add the cashflow transaction
        addCashflowTransaction(transactionId, cashflowModel.getId());
        return cashflowModel;
    }

    public Optional<CashflowModel> getCashflowByMonthAndYear(int month, int year) {
        log.info("Fetching cashflow for month: {}, year: {}", month, year);
        Optional<CashflowEntity> cashflowEntity = cashflowRepository.findByMonthAndYear(month, year);
        if (cashflowEntity.isPresent()) {
            log.info("Cashflow found: {}", cashflowEntity.get());
            return Optional.of(cashflowMapper.mapToModel(cashflowEntity.get()));
        } else {
            log.warn("No cashflow found for month: {}, year: {}", month, year);
            return Optional.empty();
        }
    }

    @Transactional
    public void updateCashflow(String transactionId, CashflowModel cashflowModel) {
        log.info("Updating cashflow: {}", cashflowModel);
        CashflowEntity cashflowEntity = cashflowRepository.findById(cashflowModel.getId())
                .orElseThrow(
                        () -> new IllegalArgumentException("Cashflow not found with id: " + cashflowModel.getId()));
        cashflowEntity.setIncomeAmount(cashflowModel.getIncomeAmount());
        cashflowEntity.setExpenseAmount(cashflowModel.getExpenseAmount());
        cashflowRepository.save(cashflowEntity);
        log.info("Updated cashflow: {}", cashflowModel);
        // Add the cashflow transaction
        addCashflowTransaction(transactionId, cashflowModel.getId());
    }

    private void addCashflowTransaction(String transactionId, String cashflowId) {
        log.info("Adding cashflow transaction: transactionId={}, cashflowId={}", transactionId, cashflowId);
        CashflowTransactionEntity cashflowTransactionEntity = new CashflowTransactionEntity();
        CashflowTransactionId cashflowTransactionId = new CashflowTransactionId();
        cashflowTransactionId.setTransactionId(transactionId);
        cashflowTransactionId.setCashflowId(cashflowId);
        cashflowTransactionEntity.setCashflowTransactionId(cashflowTransactionId);
        // Save the cashflow transaction entity to the repository
        this.cashflowTransactionRepository.save(cashflowTransactionEntity);
        log.info("Added cashflow transaction: {}", cashflowTransactionEntity);
    }

}
