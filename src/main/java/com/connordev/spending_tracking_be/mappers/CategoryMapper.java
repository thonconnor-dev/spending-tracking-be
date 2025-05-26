package com.connordev.spending_tracking_be.mappers;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.connordev.spending_tracking_be.entities.CategoryEntity;
import com.connordev.spending_tracking_be.models.CategoryModel;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;

/**
 * Mapper class responsible for converting between CategoryEntity and CategoryModel objects.
 * Uses Orika MapperFactory for object mapping with custom handling of ID fields.
 */
@Component
public class CategoryMapper {
    private MapperFactory mapperFactory;

    /**
     * Constructs a new CategoryMapper and configures the mapping rules.
     * Sets up bidirectional mapping between CategoryEntity and CategoryModel with custom ID handling.
     *
     * @param mapperFactory The Orika MapperFactory instance for configuring mappings
     */
    public CategoryMapper(MapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
        mapperFactory.classMap(CategoryEntity.class, CategoryModel.class)
        .field("name", "name")
        .exclude("id")
        .customize(new CustomMapper<CategoryEntity, CategoryModel>() {

            @Override
            public void mapAtoB(CategoryEntity a, CategoryModel b, MappingContext context) {
                b.setId(a.getId());
            }

            @Override
            public void mapBtoA(CategoryModel b, CategoryEntity a, MappingContext context) {
                if(!StringUtils.hasText(b.getId())) {
                    a.setId(UUID.randomUUID().toString());
                }
            }
        })
        .byDefault()
        .register();
    }

    /**
     * Maps a CategoryEntity to a CategoryModel.
     *
     * @param categoryEntity The source entity to map from
     * @return A new CategoryModel instance with mapped properties
     */
    public CategoryModel map(CategoryEntity categoryEntity) {
        return mapperFactory.getMapperFacade().map(categoryEntity, CategoryModel.class);
    }

    /**
     * Maps a CategoryModel to a CategoryEntity.
     *
     * @param categoryModel The source model to map from
     * @return A new CategoryEntity instance with mapped properties
     */
    public CategoryEntity map(CategoryModel categoryModel) {
        return mapperFactory.getMapperFacade().map(categoryModel, CategoryEntity.class);
    }
}
