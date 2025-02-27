package com.slippery.gamestore.repository;

import com.slippery.gamestore.models.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<WishList,Long> {
}
