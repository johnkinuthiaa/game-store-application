package com.slippery.gamestore.controller;

import com.slippery.gamestore.dto.WishListDto;
import com.slippery.gamestore.service.WishListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/wishlists")
public class WishListController {
    private final WishListService service;

    public WishListController(WishListService service) {
        this.service = service;
    }
    @PutMapping("/add/game")
    public ResponseEntity<WishListDto> addGamesToWishList(@RequestParam Long userId,@RequestParam Long gameId) {
        return ResponseEntity.ok(service.addGamesToWishList(userId,gameId));
    }
    @PutMapping("/remove/game")
    public ResponseEntity<WishListDto> removeGamesToWishList(@RequestParam Long userId,@RequestParam Long gameId) {
        return ResponseEntity.ok(service.removeGamesToWishList(userId,gameId));
    }
    @GetMapping("/games-in-wishlist")
    public ResponseEntity<WishListDto> getAllGamesInWIshList(@RequestParam Long userId){
        return ResponseEntity.ok(service.getAllGamesInWIshList(userId));
    }
    @GetMapping("/{wishlistId}/get")
    public ResponseEntity<WishListDto> findWishListById(@PathVariable Long wishlistId){
        return ResponseEntity.ok(service.findWishListById(wishlistId));
    }
}
