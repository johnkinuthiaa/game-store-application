package com.slippery.gamestore.service;

import com.slippery.gamestore.models.UserPrincipal;
import com.slippery.gamestore.models.Users;
import com.slippery.gamestore.repository.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UsersRepository repository;

    public MyUserDetailsService(UsersRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users =repository.findByUsername(username);
        if(users ==null){
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(users);
    }
}
