package com.slippery.gamestore.repository;

import com.slippery.gamestore.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
