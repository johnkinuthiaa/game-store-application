package com.slippery.gamestore.service.Impl;

import com.slippery.gamestore.dto.CategoryDto;
import com.slippery.gamestore.models.Category;
import com.slippery.gamestore.repository.CategoryRepository;
import com.slippery.gamestore.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryServiceImplementation implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImplementation(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto createNewCategory(Category category) {
        CategoryDto response =new CategoryDto();
        category.setGamesInCategory(new ArrayList<>());
        response.setMessage("New category created");
        response.setStatusCode(200);
        response.setCategory(category);
        categoryRepository.save(category);
        return response;
    }
}
