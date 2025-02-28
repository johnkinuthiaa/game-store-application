package com.slippery.gamestore.service;

import com.slippery.gamestore.dto.CommentsDto;
import com.slippery.gamestore.models.Comments;

public interface CommentService {
    CommentsDto createNewComment(Comments commentDetails,Long userId,Long gameId);
    CommentsDto deleteCommentById(Long userId,Long commentId);
    CommentsDto getCommentById(Long userId,Long commentId);
    CommentsDto getAllCommentsByUser(Long userId);


}
