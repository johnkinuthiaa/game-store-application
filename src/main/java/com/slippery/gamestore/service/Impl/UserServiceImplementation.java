package com.slippery.gamestore.service.Impl;

import com.slippery.gamestore.dto.UsersDto;
import com.slippery.gamestore.models.Users;
import com.slippery.gamestore.models.WishList;
import com.slippery.gamestore.repository.UsersRepository;
import com.slippery.gamestore.repository.WishListRepository;
import com.slippery.gamestore.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    private final UsersRepository repository;
    private final WishListRepository wishListRepository;
    private final PasswordEncoder passwordEncoder =new BCryptPasswordEncoder(12);
    private final AuthenticationManager authenticationManager;

    public UserServiceImplementation(UsersRepository repository, WishListRepository wishListRepository, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.wishListRepository = wishListRepository;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UsersDto registerUser(Users userDetails) {
        UsersDto response =new UsersDto();
        WishList wishList =new WishList();
        Users existingUser =repository.findByUsername(userDetails.getUsername());
        List<Users> existingByEmail =repository.findAll().stream()
                .filter(users -> users.getEmail().equalsIgnoreCase(userDetails.getEmail()))
                .toList();
        if(!existingByEmail.isEmpty()){
            response.setMessage("User with that email already exists");
            response.setStatusCode(401);
            return response;
        }
        if(existingUser !=null){
            response.setMessage("User with that username already exists");
            response.setStatusCode(401);
            return response;
        }

        userDetails.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        userDetails.setProfileImage(null);
        userDetails.setCreatedOn(LocalDateTime.now());
        wishList.setGamesInWishList(new ArrayList<>());
        wishList.setUser(userDetails);
        wishList.setName(userDetails.getUsername()+ "'s wishlist");
        userDetails.setWishList(wishList);
        repository.save(userDetails);
        wishListRepository.save(wishList);
        response.setMessage("New user created successfully ");
        response.setStatusCode(200);
        response.setUser(userDetails);
        return response;
    }

    @Override
    public UsersDto login(Users userDetails) {
        UsersDto response =new UsersDto();
        Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(), userDetails.getPassword()
        ));
        if(authentication.isAuthenticated()){
            response.setMessage("User is authenticated");
            response.setStatusCode(200);
        }else{
            response.setMessage("User not authenticated");
            response.setStatusCode(401);
        }
        return response;
    }

    @Override
    public UsersDto updateUser(Users userDetails, Long userId) {
        return null;
    }

    @Override
    public UsersDto findUserById(Long userId) {
        UsersDto response =new UsersDto();
        Optional<Users> user =repository.findById(userId);
        if(user.isEmpty()){
            response.setMessage("User with id "+userId+" not found");
            response.setStatusCode(404);
            return response;
        }
        response.setUser(user.get());
        response.setMessage("User with id "+userId);
        response.setStatusCode(200);
        return response;
    }

    @Override
    public UsersDto deleteUserById(Long userId) {
        UsersDto response =new UsersDto();
        var user =findUserById(userId);
        if(user.getStatusCode() !=200){
            response.setMessage("Cannot delete user because"+user.getMessage());
            response.setStatusCode(user.getStatusCode());
            return response;
        }
        repository.delete(user.getUser());
        response.setMessage("User deleted successfully");
        response.setStatusCode(200);
        return response;
    }
}
