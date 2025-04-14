package com.connordev.spending_tracking_be.mappers;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.connordev.spending_tracking_be.entities.CategoryEntity;
import com.connordev.spending_tracking_be.models.CategoryModel;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;

@Component
public class CategoryMapper {
    private MapperFactory mapperFactory;

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

    public CategoryModel map(CategoryEntity categoryEntity) {
        return mapperFactory.getMapperFacade().map(categoryEntity, CategoryModel.class);
    }

    public CategoryEntity map(CategoryModel categoryModel) {
        return mapperFactory.getMapperFacade().map(categoryModel, CategoryEntity.class);
    }
}
