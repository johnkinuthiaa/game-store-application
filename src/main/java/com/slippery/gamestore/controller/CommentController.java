package com.slippery.gamestore.controller;

import com.slippery.gamestore.dto.CommentsDto;
import com.slippery.gamestore.models.Comments;
import com.slippery.gamestore.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }
    @PostMapping("/create/game/{gameId}")
    public ResponseEntity<CommentsDto> createNewComment(@RequestBody Comments commentDetails,
                                                       @RequestParam Long userId,
                                                       @PathVariable Long gameId) {
        return ResponseEntity.ok(service.createNewComment(commentDetails, userId, gameId));

    }
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<CommentsDto> deleteCommentById(@RequestParam Long userId,@PathVariable Long commentId) {
        return ResponseEntity.ok(service.deleteCommentById(userId, commentId));
    }
    @GetMapping("/get/{commentId}")
    public ResponseEntity<CommentsDto> getCommentById(@RequestParam Long userId,@PathVariable Long commentId) {
        return ResponseEntity.ok(service.getCommentById(userId, commentId));
    }
    @GetMapping("/get/by-user")
    public ResponseEntity<CommentsDto> getAllCommentsByUser(@RequestParam Long userId){
        return ResponseEntity.ok(service.getAllCommentsByUser(userId));
    }
}
