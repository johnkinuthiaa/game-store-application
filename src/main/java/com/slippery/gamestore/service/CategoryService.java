package com.slippery.gamestore.service;

import com.slippery.gamestore.dto.CategoryDto;
import com.slippery.gamestore.models.Category;

public interface CategoryService {
    CategoryDto createNewCategory(Category category);
}
