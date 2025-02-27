package com.slippery.gamestore.controller;

import com.slippery.gamestore.dto.GameDto;
import com.slippery.gamestore.models.Game;
import com.slippery.gamestore.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/games")
public class GameController{
    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }
    @PostMapping("/add")
    public ResponseEntity<GameDto> addGame(@RequestBody Game gameDetails,@RequestParam Long categoryId) {
        return ResponseEntity.ok(service.addGame(gameDetails,categoryId));
    }

    public GameDto removeGame(Long gameId) {
        return null;
    }

    public GameDto findGameById(Long gameId) {
        return null;
    }

    public GameDto findAllGames() {
        return null;
    }

    public GameDto deleteAllGames() {
        return null;
    }

    public GameDto updateGame(Long gameId, Game updateDetails) {
        return null;
    }
}
