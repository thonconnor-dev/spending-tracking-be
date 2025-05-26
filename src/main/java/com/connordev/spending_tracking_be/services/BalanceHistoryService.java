package com.connordev.spending_tracking_be.services;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.connordev.spending_tracking_be.repositories.BalanceHistoryRepository;
import com.connordev.spending_tracking_be.entities.BalanceHistoryEntity;
import com.connordev.spending_tracking_be.models.BalanceHistoryModel;

import java.time.LocalDateTime;
import java.util.Optional;
import com.connordev.spending_tracking_be.mappers.BalanceHistoryMapper;

@Service
@Slf4j
public class BalanceHistoryService {
    private final BalanceHistoryRepository balanceHistoryRepository;
    private final BalanceHistoryMapper balanceHistoryMapper;

    public BalanceHistoryService(BalanceHistoryRepository balanceHistoryRepository,
            BalanceHistoryMapper balanceHistoryMapper) {
        this.balanceHistoryRepository = balanceHistoryRepository;
        this.balanceHistoryMapper = balanceHistoryMapper;
    }

    public Optional<BalanceHistoryModel> getLatestBalanceHistory() {
        log.info("Fetching latest balance history");
        Optional<BalanceHistoryEntity> latestBalanceHistory = balanceHistoryRepository.findTopByOrderByCreatedAtDesc();
        if (latestBalanceHistory.isPresent()) {
            log.info("Latest balance history found: {}", latestBalanceHistory.get());
            BalanceHistoryEntity entity = latestBalanceHistory.get();
            return Optional.of(balanceHistoryMapper.mapToModel(entity));
        } else {
            log.warn("No balance history found");
            return Optional.empty();
        }
    }

    public BalanceHistoryModel createBalanceHistory(Double balance) {
        log.info("Creating new balance history: {}", balance);
        BalanceHistoryModel balanceHistoryModel = BalanceHistoryModel.builder()
                .balance(balance)
                .createdDate(LocalDateTime.now())
                .build();
        BalanceHistoryEntity entity = balanceHistoryMapper.mapToEntity(balanceHistoryModel);
        log.info("Mapped BalanceHistoryModel to BalanceHistoryEntity: {}", entity);
        // Save the entity to the repository
        BalanceHistoryEntity savedEntity = balanceHistoryRepository.save(entity);
        log.info("Created balance history with ID: {}", savedEntity.getId());
        return balanceHistoryMapper.mapToModel(savedEntity);
    }

}
