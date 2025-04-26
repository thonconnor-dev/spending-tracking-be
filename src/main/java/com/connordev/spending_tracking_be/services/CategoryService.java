package com.connordev.spending_tracking_be.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.connordev.spending_tracking_be.entities.CategoryEntity;
import com.connordev.spending_tracking_be.mappers.CategoryMapper;
import com.connordev.spending_tracking_be.models.CategoryModel;
import com.connordev.spending_tracking_be.repositories.CategoryRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryService {
    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryModel> getCategories() {
        List<CategoryEntity> categories = categoryRepository.findAll();
        log.info("fetch categories size: {}", categories.size());
        return categories.stream()
                    .map(categoryMapper::map)
                    .toList();
    }

    @Transactional
    public CategoryModel createCategory(CategoryModel categoryModel) {
        log.info("creating category: {}", categoryModel);
        CategoryEntity categoryEntity = categoryRepository.save(categoryMapper.map(categoryModel));
        log.info("created category: {}", categoryEntity.getId());
        return categoryMapper.map(categoryEntity);
    }

    public CategoryModel getCategoryByName(String name) {
        log.info("fetching category by name: {}", name);
        Optional<CategoryEntity> categoryEntity = categoryRepository.findByName(name);
        return categoryEntity.map(categoryMapper::map).orElse(null);
    }
}
