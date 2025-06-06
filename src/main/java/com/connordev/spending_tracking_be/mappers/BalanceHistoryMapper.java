package com.connordev.spending_tracking_be.mappers;

import java.util.UUID;

import org.springframework.stereotype.Component;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import com.connordev.spending_tracking_be.entities.BalanceHistoryEntity;
import com.connordev.spending_tracking_be.models.BalanceHistoryModel;
import ma.glasnost.orika.MappingContext;

@Component
public class BalanceHistoryMapper {
    private MapperFactory mapperFactory;

    public BalanceHistoryMapper(MapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
        mapperFactory.classMap(BalanceHistoryEntity.class, BalanceHistoryModel.class)
                .field("balance", "balance")
                .exclude("createdAt")
                .exclude("id")
                .field("transactionId", "transaction.id")
                .customize(new CustomMapper<BalanceHistoryEntity, BalanceHistoryModel>() {
                    @Override
                    public void mapAtoB(BalanceHistoryEntity source, BalanceHistoryModel destination,
                            MappingContext context) {
                        destination.setId(source.getId());
                        destination.setCreatedDate(source.getCreatedDate() != null
                                ? source.getCreatedDate().toLocalDateTime()
                                : null);
                    }

                    @Override
                    public void mapBtoA(BalanceHistoryModel source, BalanceHistoryEntity destination,
                            MappingContext context) {
                        destination.setId(UUID.randomUUID().toString());
                        destination.setCreatedDate(source.getCreatedDate() != null
                                ? java.sql.Timestamp.valueOf(source.getCreatedDate())
                                : null);
                    }
                })
                .byDefault()
                .register();
    }

    public BalanceHistoryEntity mapToEntity(BalanceHistoryModel model) {
        return mapperFactory.getMapperFacade().map(model, BalanceHistoryEntity.class);
    }

    public BalanceHistoryModel mapToModel(BalanceHistoryEntity entity) {
        return mapperFactory.getMapperFacade().map(entity, BalanceHistoryModel.class);
    }

}
