package com.slippery.gamestore.service;

import com.slippery.gamestore.dto.GameDto;
import com.slippery.gamestore.models.Game;

public interface GameService {
    GameDto addGame(Game gameDetails);
    GameDto removeGame(Long gameId);
    GameDto findGameById(Long gameId);
    GameDto findAllGames();
    GameDto deleteAllGames();
    GameDto updateGame(Long gameId,Game updateDetails);
}
