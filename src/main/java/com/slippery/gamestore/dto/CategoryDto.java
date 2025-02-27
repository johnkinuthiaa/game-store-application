package com.slippery.gamestore.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.slippery.gamestore.models.Category;
import com.slippery.gamestore.models.Game;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryDto {
    private String message;
    private Integer statusCode;
    private Category category;
    private List<Category> categories;
    private List<Game> games;
}
