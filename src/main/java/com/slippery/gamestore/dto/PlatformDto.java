package com.slippery.gamestore.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.slippery.gamestore.models.Platform;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlatformDto {
    private String message;
    private Integer statusCode;
    private Platform platform;
    private List<Platform> platforms;
}
