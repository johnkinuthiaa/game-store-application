package com.slippery.gamestore.controller;

import com.slippery.gamestore.dto.CategoryDto;
import com.slippery.gamestore.models.Category;
import com.slippery.gamestore.service.CategoryService;
import com.slippery.gamestore.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }
    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createNewCategory(@RequestBody Category category) {
        return ResponseEntity.ok(service.createNewCategory(category));
    }

    public ResponseEntity<CategoryDto>  updateCategory(Long categoryId, Category category) {
        return null;
    }
    @GetMapping("/names")
    public ResponseEntity<CategoryDto>  findCategoryByName(@RequestParam String categoryName) {
        return ResponseEntity.ok(service.findCategoryByName(categoryName));
    }
    @GetMapping("/{categoryId}/get")
    public ResponseEntity<CategoryDto>  findCategoryById(@PathVariable Long categoryId) {
        return ResponseEntity.ok(service.findCategoryById(categoryId));
    }
    @DeleteMapping("/{categoryId}/delete")
    public ResponseEntity<CategoryDto>  deleteCategoryById(@PathVariable Long categoryId) {
        return ResponseEntity.ok(service.deleteCategoryById(categoryId));
    }
    @GetMapping("/get/all")
    public ResponseEntity<CategoryDto>  findAllCategories() {
        return ResponseEntity.ok(service.findAllCategories());
    }
    @GetMapping("/{categoryId}/games")
    public ResponseEntity<CategoryDto>  findAllGamesInCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(service.findAllGamesInCategory(categoryId));
    }
}
