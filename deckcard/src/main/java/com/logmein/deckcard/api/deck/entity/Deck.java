package com.logmein.deckcard.api.deck.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.logmein.deckcard.api.game.entity.Game;

import javax.persistence.*;
import java.util.List;

@Entity
public class Deck {
    @Id
    @GeneratedValue
    private Long id;
    private String name;


    @OneToMany(mappedBy="deck")
    private List<DeckCard> deckCards;

    @JsonIgnore
    @ManyToOne
    private Game game;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DeckCard> getDeckCards() {
        return deckCards;
    }

    public void setDeckCards(List<DeckCard> deckCards) {
        this.deckCards = deckCards;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
