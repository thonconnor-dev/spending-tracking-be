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

/**
 * Service for managing spending categories.
 * Provides operations to create, retrieve, and search categories.
 */
@Service
@Slf4j
public class CategoryService {
    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    /**
     * Constructs a new CategoryService with required dependencies.
     *
     * @param categoryRepository Repository for category data operations
     * @param categoryMapper Mapper for converting between entity and model objects
     */
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    /**
     * Retrieves all categories from the database.
     *
     * @return List of category models
     */
    public List<CategoryModel> getCategories() {
        List<CategoryEntity> categories = categoryRepository.findAll();
        log.info("fetch categories size: {}", categories.size());
        return categories.stream()
                    .map(categoryMapper::map)
                    .toList();
    }

    /**
     * Creates a new category in the database.
     *
     * @param categoryModel The category model containing data to be saved
     * @return The saved category model with generated ID
     */
    @Transactional
    public CategoryModel createCategory(CategoryModel categoryModel) {
        log.info("creating category: {}", categoryModel);
        CategoryEntity categoryEntity = categoryRepository.save(categoryMapper.map(categoryModel));
        log.info("created category: {}", categoryEntity.getId());
        return categoryMapper.map(categoryEntity);
    }

    /**
     * Finds a category by its name.
     *
     * @param name The name of the category to find
     * @return The category model if found, null otherwise
     */
    public CategoryModel getCategoryByName(String name) {
        log.info("fetching category by name: {}", name);
        Optional<CategoryEntity> categoryEntity = categoryRepository.findByName(name);
        return categoryEntity.map(categoryMapper::map).orElse(null);
    }
}
