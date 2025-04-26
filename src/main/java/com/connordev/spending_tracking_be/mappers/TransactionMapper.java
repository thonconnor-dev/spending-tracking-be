package com.connordev.spending_tracking_be.mappers;


import java.util.UUID;

import org.springframework.stereotype.Component;

import com.connordev.spending_tracking_be.entities.TransactionEntity;
import com.connordev.spending_tracking_be.models.CategoryModel;
import com.connordev.spending_tracking_be.models.TransactionModel;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;

@Component
public class TransactionMapper {
    private MapperFactory mapperFactory;

    public TransactionMapper(MapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
        mapperFactory.classMap(TransactionEntity.class, TransactionModel.class)
        .field("name", "name")
        .field("amount", "amount")
        .field("transactionType", "transactionType")
        .exclude("id")
        .exclude("createdDate")
        .exclude("category")
        .exclude("categoryId")
        .customize(new CustomMapper<TransactionEntity, TransactionModel>() {

            @Override
            public void mapAtoB(TransactionEntity a, TransactionModel b, MappingContext context) {
                b.setId(a.getId());
                if(a.getCreatedDate() != null) {
                    // b.setCreatedDate(a.getCreatedDate().()));
                }
                CategoryModel categoryModel = CategoryModel.builder()
                                                            .id(a.getCategoryId())
                                                            .build();
                b.setCategory(categoryModel);
            }

            @Override
            public void mapBtoA(TransactionModel b, TransactionEntity a, MappingContext context) {
                a.setCategoryId(b.getCategory().getId());
                a.setId(UUID.randomUUID().toString());
                if(b.getCreatedDate() != null) {
                    // a.setCreatedDate(b.getCreatedDate());
                }
            }

        })
        .byDefault()
        .register();
    }

    public TransactionModel map(TransactionEntity transaction) {
        return mapperFactory.getMapperFacade().map(transaction, TransactionModel.class);
    }

    public TransactionEntity map(TransactionModel transactionModel) {
        return mapperFactory.getMapperFacade().map(transactionModel, TransactionEntity.class);
    }

}
