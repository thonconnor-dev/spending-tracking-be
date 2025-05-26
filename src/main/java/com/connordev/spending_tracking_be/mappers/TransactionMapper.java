package com.connordev.spending_tracking_be.mappers;


import java.util.UUID;

import org.springframework.stereotype.Component;

import com.connordev.spending_tracking_be.entities.TransactionEntity;
import com.connordev.spending_tracking_be.models.CategoryModel;
import com.connordev.spending_tracking_be.models.TransactionModel;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;

/**
 * Mapper class responsible for converting between TransactionEntity and TransactionModel objects.
 * Uses Orika MapperFactory for object mapping with custom handling of ID fields, dates, and category relationships.
 */
@Component
public class TransactionMapper {
    private MapperFactory mapperFactory;

    /**
     * Constructs a new TransactionMapper and configures the mapping rules.
     * Sets up bidirectional mapping between TransactionEntity and TransactionModel with custom handling
     * for IDs, dates, and category relationships.
     *
     * @param mapperFactory The Orika MapperFactory instance for configuring mappings
     */
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

    /**
     * Maps a TransactionEntity to a TransactionModel.
     * Includes mapping of associated category information and handling of dates.
     *
     * @param transaction The source entity to map from
     * @return A new TransactionModel instance with mapped properties
     */
    public TransactionModel map(TransactionEntity transaction) {
        return mapperFactory.getMapperFacade().map(transaction, TransactionModel.class);
    }

    /**
     * Maps a TransactionModel to a TransactionEntity.
     * Handles the mapping of category relationships and generates new IDs for new transactions.
     *
     * @param transactionModel The source model to map from
     * @return A new TransactionEntity instance with mapped properties
     */
    public TransactionEntity map(TransactionModel transactionModel) {
        return mapperFactory.getMapperFacade().map(transactionModel, TransactionEntity.class);
    }

}
