package com.slippery.gamestore.service.Impl;

import com.slippery.gamestore.dto.CategoryDto;
import com.slippery.gamestore.models.Category;
import com.slippery.gamestore.repository.CategoryRepository;
import com.slippery.gamestore.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public CategoryDto updateCategory(Long categoryId, Category category) {
        return null;
    }

    @Override
    public CategoryDto findCategoryByName(String categoryName) {
        CategoryDto response =new CategoryDto();
        List<Category> category =categoryRepository.findAll().stream()
                .filter(category1 -> category1.getName().equalsIgnoreCase(categoryName))
                .toList();
        response.setCategories(category);
        response.setMessage("Categories with the name "+categoryName);
        response.setStatusCode(200);
        return response;
    }

    @Override
    public CategoryDto findCategoryById(Long categoryId) {
        CategoryDto response =new CategoryDto();
        Optional<Category>category =categoryRepository.findById(categoryId);
        if(category.isEmpty()){
            response.setMessage("No category with id"+categoryId);
            response.setStatusCode(404);
            return response;
        }

        response.setCategory(category.get());
        response.setMessage("Category with id "+categoryId);
        response.setStatusCode(200);
        return response;
    }

    @Override
    public CategoryDto deleteCategoryById(Long categoryId) {
        CategoryDto response =new CategoryDto();
        var existingCategory =findCategoryById(categoryId);
        if(existingCategory.getStatusCode() ==404){
            return existingCategory;
        }
        categoryRepository.delete(existingCategory.getCategory());
        response.setMessage("Category deleted");
        response.setStatusCode(200);
        return response;
    }

    @Override
    public CategoryDto findAllCategories() {
        CategoryDto response =new CategoryDto();
        response.setCategories(categoryRepository.findAll());
        response.setMessage("All categories");
        response.setStatusCode(200);
        return response;
    }

    @Override
    public CategoryDto findAllGamesInCategory(Long categoryId) {
        CategoryDto response =new CategoryDto();
        var existingCategory =findCategoryById(categoryId);
        if(existingCategory.getStatusCode() ==404){
            return existingCategory;
        }
        var games =existingCategory.getCategory().getGamesInCategory();
        response.setMessage("All games in "+existingCategory.getCategory().getName());
        response.setStatusCode(200);
        response.setGames(games);
        return response;
    }
}
