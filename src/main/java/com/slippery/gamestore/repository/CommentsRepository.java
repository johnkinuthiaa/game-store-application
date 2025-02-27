package com.slippery.gamestore.repository;

import com.slippery.gamestore.models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments,Long> {
}
