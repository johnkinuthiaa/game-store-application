package com.slippery.gamestore.controller;

import com.slippery.gamestore.dto.CategoryDto;
import com.slippery.gamestore.models.Category;
import com.slippery.gamestore.service.CategoryService;
import com.slippery.gamestore.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }
    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createNewCategory(@RequestBody Category category) {
        return ResponseEntity.ok(service.createNewCategory(category));
    }
}
