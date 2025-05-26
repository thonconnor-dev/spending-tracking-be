package com.connordev.spending_tracking_be.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.connordev.spending_tracking_be.entities.TransactionEntity;
import com.connordev.spending_tracking_be.enums.TransactionType;
import com.connordev.spending_tracking_be.mappers.TransactionMapper;
import com.connordev.spending_tracking_be.models.BalanceHistoryModel;
import com.connordev.spending_tracking_be.models.CashflowModel;
import com.connordev.spending_tracking_be.models.TransactionModel;
import com.connordev.spending_tracking_be.repositories.TransactionRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionService {
    private TransactionMapper transactionMapper;
    private TransactionRepository transactionRepository;
    private BalanceHistoryService balanceHistoryService;
    private CashflowService cashflowService;

    public TransactionService(TransactionMapper transactionMapper, TransactionRepository transactionRepository,
            BalanceHistoryService balanceHistoryService, CashflowService cashflowService) {
        this.transactionMapper = transactionMapper;
        this.transactionRepository = transactionRepository;
        this.balanceHistoryService = balanceHistoryService;
        this.cashflowService = cashflowService;
    }

    @Transactional
    public TransactionModel createTransaction(TransactionModel transactionModel) {
        // get latest balance history
        log.info("creating transaction: {}", transactionModel);
        Optional<BalanceHistoryModel> latestBalanceHistory = balanceHistoryService.getLatestBalanceHistory();
        Double currentBalance = 0.0;
        if (latestBalanceHistory.isPresent()) {
            log.info("Latest balance history found: {}", latestBalanceHistory.get());
            currentBalance = latestBalanceHistory.get().getBalance();
        } else {
            log.warn("No balance history found, initializing with zero balance");
        }

        TransactionType transactionType = transactionModel.getTransactionType();
        log.info("Transaction type: {}", transactionType);

        if (transactionType == TransactionType.INCOME) {
            // income -> add to balance
            currentBalance += transactionModel.getAmount();
        } else if (transactionType == TransactionType.EXPENSE) {
            // expense -> subtract from balance
            currentBalance -= transactionModel.getAmount();
        }

        log.info("Updated current balance: {}", currentBalance);
        balanceHistoryService.createBalanceHistory(currentBalance);
        // create new balance history
        TransactionEntity transactionEntity = transactionMapper.map(transactionModel);
        transactionRepository.save(transactionEntity);
        log.info("Transaction saved with ID: {}", transactionEntity.getId());

        int month = transactionModel.getCreatedDate().getMonthValue();
        int year = transactionModel.getCreatedDate().getYear();
        log.info("Fetching cashflow for month: {}, year: {}", month, year);
        Optional<CashflowModel> cashflowModelOpt = cashflowService
                .getCashflowByMonthAndYear(month, year);

        if (cashflowModelOpt.isPresent()) {
            log.info("Cashflow found for month: {}, year: {}", month, year);
        } else {
            log.info("No cashflow found for month: {}, year: {}, creating new cashflow record", month, year);
            // create new cashflow record
            cashflowService.createNewCashflow(month, year, 0.0, 0.0);
        }
        return transactionModel;
    }

    public TransactionModel getTransaction(String id) {
        // get transaction by id
        log.info("fetching transaction by id: {}", id);
        Optional<TransactionEntity> transactonEntity = transactionRepository.findById(id);
        return transactonEntity.map(transactionMapper::map).orElse(null);
    }

    public List<TransactionModel> getTransactions(LocalDate startDate, LocalDate endDate) {
        log.info("fetching transactions by date range: {} to {}", startDate, endDate);
        // List<TransactionEntity> transactions =
        // transactionRepository.findByCreatedDateBetween(startDate, endDate);
        // return transactions.stream()
        // .map(transactionMapper::map)
        // .toList();
        return null;
    }
}
