package com.slippery.gamestore.service.Impl;

import com.slippery.gamestore.dto.CommentsDto;
import com.slippery.gamestore.dto.GameDto;
import com.slippery.gamestore.models.Comments;
import com.slippery.gamestore.models.Game;
import com.slippery.gamestore.models.Users;
import com.slippery.gamestore.repository.CommentsRepository;
import com.slippery.gamestore.repository.GameRepository;
import com.slippery.gamestore.repository.UsersRepository;
import com.slippery.gamestore.service.CommentService;
import com.slippery.gamestore.service.GameService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImplementation implements CommentService {
    private final CommentsRepository repository;
    private final UsersRepository usersRepository;
    private final GameRepository gameRepository;
    private final GameService gameService;

    public CommentServiceImplementation(CommentsRepository repository, UsersRepository usersRepository, GameRepository gameRepository, GameService gameService) {
        this.repository = repository;
        this.usersRepository = usersRepository;
        this.gameRepository = gameRepository;
        this.gameService = gameService;
    }

    @Override
    public CommentsDto createNewComment(Comments commentDetails, Long userId, Long gameId) {
        CommentsDto response =new CommentsDto();
        Optional<Users> existingUser =usersRepository.findById(userId);
        GameDto existingGame =gameService.findGameById(gameId);
        if(existingGame.getStatusCode() !=200){
            response.setMessage(existingGame.getMessage());
            response.setStatusCode(existingGame.getStatusCode());
            return response;
        }
        if(existingUser.isEmpty()){
            response.setMessage("User not found");
            response.setStatusCode(404);
            return response;
        }
        commentDetails.setUser(existingUser.get());
        commentDetails.setGame(existingGame.getGame());
        var comments =existingGame.getGame().getCommentsForGame();
        comments.add(commentDetails);
        existingGame.getGame().setCommentsForGame(comments);
        repository.save(commentDetails);
        gameRepository.save(existingGame.getGame());
        response.setMessage("New comment for game added");
        response.setStatusCode(200);
        response.setComment(commentDetails);
        return response;
    }

    @Override
    public CommentsDto deleteCommentById(Long userId, Long commentId) {
        CommentsDto response =new CommentsDto();
        CommentsDto existingComment =getCommentById(userId, commentId);
        if(existingComment.getStatusCode() !=200){
            return existingComment;
        }
        var commentsInGame =existingComment.getComment().getGame().getCommentsForGame();
        commentsInGame.remove(existingComment.getComment());
        Game game = existingComment.getComment().getGame();
        game.setCommentsForGame(commentsInGame);
//        gameRepository.save(game);
        repository.delete(existingComment.getComment());
        response.setMessage("Comment deleted successfully");
        response.setStatusCode(200);
        return response;
    }

    @Override
    public CommentsDto getCommentById(Long userId, Long commentId) {
        CommentsDto response =new CommentsDto();
        Optional<Comments> existingComment =repository.findById(commentId);
        Optional<Users> existingUser =usersRepository.findById(userId);
        if(existingUser.isEmpty()){
            response.setMessage("User not found");
            response.setStatusCode(404);
            return response;
        }
        if(existingComment.isEmpty()){
            response.setMessage("Comment does not exist!");
            response.setStatusCode(404);
            return response;
        }
        if(!existingComment.get().getUser().getId().equals(existingUser.get().getId())){
            response.setMessage("Comment does not belong that user");
            response.setStatusCode(401);
            return response;
        }
        response.setComment(existingComment.get());
        response.setMessage("Comment with id"+commentId);
        response.setStatusCode(200);
        return response;
    }

    @Override
    public CommentsDto getAllCommentsByUser(Long userId) {
        CommentsDto response =new CommentsDto();
        Optional<Users> existingUser =usersRepository.findById(userId);
        if(existingUser.isEmpty()){
            response.setMessage("User not found");
            response.setStatusCode(404);
            return response;
        }
        var commentsByUser =repository.findAll().stream()
                .filter(comments -> comments.getUser().getId().equals(existingUser.get().getId()))
                .toList();
        response.setStatusCode(200);
        response.setMessage("All comments by user");
        response.setComments(commentsByUser);
        return response;
    }
}
