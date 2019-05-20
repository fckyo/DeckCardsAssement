package com.logmein.deckcard.api.player.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.logmein.deckcard.api.deck.entity.DeckCard;
import com.logmein.deckcard.api.game.entity.Game;

import javax.persistence.*;
import java.util.List;

@Entity
public class Player {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinTable(
                    name="players_cards",
                    joinColumns={ @JoinColumn(name="player_id", referencedColumnName="id") },
                    inverseJoinColumns={ @JoinColumn(name="deck_card_id", referencedColumnName="id", unique=true) }
                    )
    List<DeckCard> cardsPerPlayers;

    @JsonIgnore
    @ManyToOne
    private Game game;

    @Transient
    private Long totalCardsValue;

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

    public List<DeckCard> getCardsPerPlayers() {
        return cardsPerPlayers;
    }

    public void setCardsPerPlayers(List<DeckCard> cardsPerPlayers) {
        this.cardsPerPlayers = cardsPerPlayers;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Long getTotalCardsValue() {
        if(cardsPerPlayers == null || cardsPerPlayers.isEmpty())
            return 0L;

        totalCardsValue = cardsPerPlayers.stream().mapToLong(value -> value.getCard().getValue()).sum();

        return totalCardsValue;
    }

    public void setTotalCardsValue(Long totalCardsValue) {
        this.totalCardsValue = totalCardsValue;
    }
}
