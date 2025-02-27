package com.slippery.gamestore.service.Impl;

import com.slippery.gamestore.dto.GameDto;
import com.slippery.gamestore.models.Game;
import com.slippery.gamestore.repository.CategoryRepository;
import com.slippery.gamestore.repository.GameRepository;
import com.slippery.gamestore.service.GameService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class GameServiceImplementation implements GameService {
    private final GameRepository gameRepository;
    private final CategoryRepository categoryRepository;

    public GameServiceImplementation(GameRepository gameRepository, CategoryRepository categoryRepository) {
        this.gameRepository = gameRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public GameDto addGame(Game gameDetails,Long categoryId) {
        GameDto response =new GameDto();
        var existingCategory =categoryRepository.findById(categoryId);
        if(existingCategory.isEmpty()){
            response.setMessage("category does not exist");
            response.setStatusCode(200);
            return response;
        }
        gameDetails.setCategory(existingCategory.get());
        gameDetails.setAddedOn(LocalDateTime.now());
        gameDetails.setCommentsForGame(new ArrayList<>());
        gameDetails.setWishLists(new ArrayList<>());
        gameDetails.setGameImage(null);
        gameDetails.setPlatformsSupported(gameDetails.getPlatformsSupported());
        gameRepository.save(gameDetails);

        var gamesInCategory =existingCategory.get().getGamesInCategory();
        gamesInCategory.add(gameDetails);
        existingCategory.get().setGamesInCategory(gamesInCategory);
        categoryRepository.save(existingCategory.get());

        response.setMessage("New game added");
        response.setStatusCode(200);
        response.setGame(gameDetails);
        return response;
    }

    @Override
    public GameDto removeGame(Long gameId) {
        return null;
    }

    @Override
    public GameDto findGameById(Long gameId) {
        return null;
    }

    @Override
    public GameDto findAllGames() {
        return null;
    }

    @Override
    public GameDto deleteAllGames() {
        return null;
    }

    @Override
    public GameDto updateGame(Long gameId, Game updateDetails) {
        return null;
    }
}
