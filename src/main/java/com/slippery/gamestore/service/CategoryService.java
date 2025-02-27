package com.slippery.gamestore.service;

import com.slippery.gamestore.dto.CategoryDto;
import com.slippery.gamestore.models.Category;

public interface CategoryService {
    CategoryDto createNewCategory(Category category);
    CategoryDto updateCategory(Long categoryId,Category category);
    CategoryDto findCategoryByName(String categoryName);
    CategoryDto findCategoryById(Long categoryId);
    CategoryDto deleteCategoryById(Long categoryId);
    CategoryDto findAllCategories();
    CategoryDto findAllGamesInCategory(Long categoryId);
}
