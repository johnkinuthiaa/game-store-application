package com.slippery.gamestore.service;

import com.slippery.gamestore.dto.WishListDto;

public interface WishListService {
    WishListDto addGamesToWishList(Long userId,Long gameId);
    WishListDto removeGamesToWishList(Long userId,Long gameId);
    WishListDto getAllGamesInWIshList(Long userId);
    WishListDto findWishListById(Long wishListId);
}
