package com.slippery.gamestore.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.slippery.gamestore.models.Comments;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentsDto {
    private String message;
    private Integer statusCode;
    private Comments comment;
    private List<Comments> comments;
}
