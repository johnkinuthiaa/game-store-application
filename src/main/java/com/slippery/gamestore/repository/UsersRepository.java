package com.slippery.gamestore.repository;

import com.slippery.gamestore.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long> {
}
