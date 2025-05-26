package com.connordev.spending_tracking_be.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.connordev.spending_tracking_be.entities.CashflowEntity;
import com.connordev.spending_tracking_be.mappers.CashflowMapper;
import com.connordev.spending_tracking_be.models.CashflowModel;
import com.connordev.spending_tracking_be.repositories.CashflowRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CashflowService {
    private CashflowRepository cashflowRepository;
    private final CashflowMapper cashflowMapper;

    public CashflowService(CashflowRepository cashflowRepository, CashflowMapper cashflowMapper) {
        this.cashflowMapper = cashflowMapper;
        this.cashflowRepository = cashflowRepository;
    }

    public CashflowModel createNewCashflow(int month, int year, double incomeAmount, double expenseAmount) {
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

        log.info("Created new cashflow: {}", cashflowModel);
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

}
