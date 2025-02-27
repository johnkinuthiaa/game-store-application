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
    @DeleteMapping("/{gameId}/delete")
    public ResponseEntity<GameDto> removeGame(@PathVariable Long gameId) {
        return ResponseEntity.ok(service.removeGame(gameId));
    }
    @GetMapping("/{gameId}/get")
    public ResponseEntity<GameDto> findGameById(@PathVariable Long gameId) {
        return ResponseEntity.ok(service.findGameById(gameId));
    }
    @GetMapping("/all")
    public ResponseEntity<GameDto> findAllGames() {
        return ResponseEntity.ok(service.findAllGames());
    }
    @DeleteMapping("/delete/all")
    public ResponseEntity<GameDto> deleteAllGames() {
        return ResponseEntity.ok(service.deleteAllGames());
    }
    @PutMapping("/{gameId}/update")
    public ResponseEntity<GameDto> updateGame(@PathVariable Long gameId,@RequestBody Game updateDetails) {
        return ResponseEntity.ok(service.updateGame(gameId, updateDetails));
    }
}
