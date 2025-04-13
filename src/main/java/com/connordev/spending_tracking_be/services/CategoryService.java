package com.connordev.spending_tracking_be.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connordev.spending_tracking_be.models.CategoryModel;
import com.connordev.spending_tracking_be.repositories.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryModel> getCategories() {
        return null;
    }
}
