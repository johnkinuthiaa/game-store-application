package com.slippery.gamestore.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne
    private Category category;
    @Lob
    private List<String> platformsSupported =new ArrayList<>();
    @ManyToMany
    private List<WishList> wishLists;
    @Lob
    private byte[] gameImage;
    private LocalDateTime addedOn;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comments> commentsForGame;
}
