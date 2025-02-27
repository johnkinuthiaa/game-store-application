package com.slippery.gamestore.service.Impl;

import com.slippery.gamestore.dto.GameDto;
import com.slippery.gamestore.models.Comments;
import com.slippery.gamestore.models.Game;
import com.slippery.gamestore.models.WishList;
import com.slippery.gamestore.repository.CategoryRepository;
import com.slippery.gamestore.repository.CommentsRepository;
import com.slippery.gamestore.repository.GameRepository;
import com.slippery.gamestore.repository.WishListRepository;
import com.slippery.gamestore.service.GameService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class GameServiceImplementation implements GameService {
    private final GameRepository gameRepository;
    private final CategoryRepository categoryRepository;
    private final CommentsRepository commentsRepository;
    private final WishListRepository wishListRepository;

    public GameServiceImplementation(GameRepository gameRepository, CategoryRepository categoryRepository, CommentsRepository commentsRepository, WishListRepository wishListRepository) {
        this.gameRepository = gameRepository;
        this.categoryRepository = categoryRepository;
        this.commentsRepository = commentsRepository;
        this.wishListRepository = wishListRepository;
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
        GameDto response =new GameDto();
        var existingGame =findGameById(gameId);
        if(existingGame.getStatusCode() ==404){
            return existingGame;
        }

//        remove game from category
        var category = existingGame.getGame().getCategory();
        var games =category.getGamesInCategory();
        games.remove(existingGame.getGame());
        category.setGamesInCategory(games);
        categoryRepository.save(category);

//        remove game from wishlist
        var wishlist =existingGame.getGame().getWishLists();

        gameRepository.delete(existingGame.getGame());

        response.setStatusCode(200);
        response.setMessage(existingGame.getMessage()+" deleted successfully");
        return response;
    }

    @Override
    public GameDto findGameById(Long gameId) {
        GameDto response =new GameDto();
        var existingGame =gameRepository.findById(gameId);
        if(existingGame.isEmpty()){
            response.setMessage("Game with id "+gameId+" does not exist");
            response.setStatusCode(404);
            return response;
        }
        response.setGame(existingGame.get());

        response.setMessage("Game with id "+gameId);
        response.setGame(existingGame.get());
        response.setStatusCode(200);
        return response;
    }

    @Override
    public GameDto findAllGames() {
        GameDto response =new GameDto();
        var allGames =gameRepository.findAll();
        response.setGameList(allGames);
        response.setMessage("All games in store");
        response.setStatusCode(200);
        return response;
    }

    @Override
    public GameDto deleteAllGames() {
        GameDto response =new GameDto();
        gameRepository.deleteAll();
        response.setMessage("All games in store deleted");
        response.setStatusCode(200);
        return response;
    }

    @Override
    public GameDto updateGame(Long gameId, Game updateDetails) {
        return null;
    }
}
