package com.connordev.spending_tracking_be.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.connordev.spending_tracking_be.entities.TransactionEntity;
import com.connordev.spending_tracking_be.mappers.TransactionMapper;
import com.connordev.spending_tracking_be.models.TransactionModel;
import com.connordev.spending_tracking_be.repositories.TransactionRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionService {
    private TransactionMapper transactionMapper;
    private TransactionRepository transactionRepository;

    public TransactionService(TransactionMapper transactionMapper, TransactionRepository transactionRepository) {
        this.transactionMapper = transactionMapper;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public TransactionModel createTransaction(TransactionModel transactionModel) {
        // get latest balance history

        // no balance history -> amount is 0

        // check transaction type

        // income -> add to balance

        // expense -> subtract from balance

        // create new balance history

        // create new transaction

        // get cashflow record by month and year

        // cashflow exists -> update income or expense amount

        // cashflow does not exist -> create new cashflow record

        // create cashflow transaction record

        // return transaction model
        return null;
    }

    public TransactionModel getTransaction(String id) {
        log.info("fetching transaction by id: {}", id);
        Optional<TransactionEntity> transactonEntity = transactionRepository.findById(id);
        return transactonEntity.map(transactionMapper::map).orElse(null);
    }
    
    public List<TransactionModel> getTransactions(LocalDate startDate, LocalDate endDate) {
        log.info("fetching transactions by date range: {} to {}", startDate, endDate);
        // List<TransactionEntity> transactions = transactionRepository.findByCreatedDateBetween(startDate, endDate);
        // return transactions.stream()
        //         .map(transactionMapper::map)
        //         .toList();
        return null;
    }
}
