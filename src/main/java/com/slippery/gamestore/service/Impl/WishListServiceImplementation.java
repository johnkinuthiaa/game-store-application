package com.slippery.gamestore.service.Impl;

import com.slippery.gamestore.dto.GameDto;
import com.slippery.gamestore.dto.UsersDto;
import com.slippery.gamestore.dto.WishListDto;
import com.slippery.gamestore.models.WishList;
import com.slippery.gamestore.repository.GameRepository;
import com.slippery.gamestore.repository.WishListRepository;
import com.slippery.gamestore.service.GameService;
import com.slippery.gamestore.service.UserService;
import com.slippery.gamestore.service.WishListService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WishListServiceImplementation implements WishListService {
    private final WishListRepository repository;
    private final UserService userService;
    private final GameService gameService;
    private final GameRepository gameRepository;

    public WishListServiceImplementation(WishListRepository repository, UserService userService, GameService gameService, GameRepository gameRepository) {
        this.repository = repository;
        this.userService = userService;
        this.gameService = gameService;
        this.gameRepository = gameRepository;
    }

    @Override
    public WishListDto addGamesToWishList(Long userId, Long gameId) {
        WishListDto response =new WishListDto();
        var existingGame =gameService.findGameById(gameId);
        var existingUser =userService.findUserById(userId);
        if(existingGame.getStatusCode() !=200){
            response.setStatusCode(existingGame.getStatusCode());
            response.setMessage(existingGame.getMessage());
            return response;
        }
        if(existingUser.getStatusCode() !=200){
            response.setStatusCode(existingUser.getStatusCode());
            response.setMessage(existingUser.getMessage());
            return response;
        }

        var wishlist =existingUser.getUser().getWishList();
        var gamesInWishList =wishlist.getGamesInWishList();
        if(gamesInWishList.contains(existingGame.getGame())){
            response.setMessage(existingGame.getGame().getTitle()+" is already in "+wishlist.getName());
            response.setStatusCode(300);
            return response;
        }
        gamesInWishList.add(existingGame.getGame());
        wishlist.setGamesInWishList(gamesInWishList);

        var totalWishListsForGame =existingGame.getGame().getWishLists();
        totalWishListsForGame.add(wishlist);
        existingGame.getGame().setWishLists(totalWishListsForGame);

        repository.save(wishlist);
        gameRepository.save(existingGame.getGame());
        response.setMessage(existingGame.getGame().getTitle()+" Game added to"+wishlist.getName()+".");
        response.setStatusCode(200);
        return response;
    }

    @Override
    public WishListDto removeGamesToWishList(Long userId, Long gameId) {
        WishListDto response =new WishListDto();
        var existingGame =gameService.findGameById(gameId);
        var existingUser =userService.findUserById(userId);

        if(existingUser.getStatusCode() !=200){
            response.setStatusCode(existingUser.getStatusCode());
            response.setMessage(existingUser.getMessage());
            return response;
        }
        if(existingGame.getStatusCode() !=200){
            response.setStatusCode(existingGame.getStatusCode());
            response.setMessage(existingGame.getMessage());
            return response;
        }
        var wishList =existingUser.getUser().getWishList();
        var wishListsForGame =existingGame.getGame().getWishLists();
        wishListsForGame.remove(wishList);

        var gamesInWishList =wishList.getGamesInWishList();
        gamesInWishList.remove(existingGame.getGame());
        gameRepository.save(existingGame.getGame());
        repository.save(wishList);
        response.setMessage(existingGame.getGame().getTitle()+" removed from "+wishList.getName());
        response.setStatusCode(200);
        return response;
    }

    @Override
    public WishListDto getAllGamesInWIshList(Long userId) {
        WishListDto response =new WishListDto();
        var existingUser =userService.findUserById(userId);
        if(existingUser.getStatusCode() !=200){
            response.setStatusCode(existingUser.getStatusCode());
            response.setMessage(existingUser.getMessage());
            return response;
        }
        var wishlist =existingUser.getUser().getWishList();
        response.setGameList(wishlist.getGamesInWishList());
        response.setStatusCode(200);
        response.setMessage("All games in "+wishlist.getName());
        return response;
    }

    @Override
    public WishListDto findWishListById(Long wishListId) {
        WishListDto response =new WishListDto();
        Optional<WishList>wishList =repository.findById(wishListId);
        if(wishList.isEmpty()){
            response.setMessage("Wishlist does not exist");
            response.setStatusCode(404);
            return response;
        }
        response.setWishList(wishList.get());
        response.setStatusCode(200);
        response.setMessage("Wishlist with id"+wishListId);
        return response;
    }
}
