package com.slippery.gamestore.repository;

import com.slippery.gamestore.models.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformRepository extends JpaRepository<Platform,Long> {
}
