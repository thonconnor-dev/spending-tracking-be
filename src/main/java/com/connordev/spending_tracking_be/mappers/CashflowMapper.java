package com.connordev.spending_tracking_be.mappers;

import org.springframework.stereotype.Component;

import ma.glasnost.orika.MapperFactory;

import com.connordev.spending_tracking_be.entities.CashflowEntity;
import com.connordev.spending_tracking_be.models.CashflowModel;

@Component
public class CashflowMapper {
    private final MapperFactory mapperFactory;

    public CashflowMapper(MapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
        mapperFactory.classMap(CashflowEntity.class, CashflowModel.class)
                .field("id", "id")
                .field("amount", "incomeAmount")
                .field("expenseAmount", "expenseAmount")
                .field("description", "description")
                .field("createdAt", "createdDate")
                .byDefault()
                .register();
    }

    public CashflowEntity mapToEntity(CashflowModel model) {
        return mapperFactory.getMapperFacade().map(model, CashflowEntity.class);
    }

    public CashflowModel mapToModel(CashflowEntity entity) {
        return mapperFactory.getMapperFacade().map(entity, CashflowModel.class);
    }

}
