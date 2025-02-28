package com.slippery.gamestore.service;

import com.slippery.gamestore.dto.UsersDto;
import com.slippery.gamestore.models.Users;

public interface UserService {
    UsersDto registerUser(Users userDetails);
    UsersDto login(Users userDetails);
    UsersDto updateUser(Users userDetails,Long userId);
    UsersDto findUserById(Long userId);
    UsersDto deleteUserById(Long userId);

}
