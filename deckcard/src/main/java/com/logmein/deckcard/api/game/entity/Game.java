package com.logmein.deckcard.api.game.entity;

import com.logmein.deckcard.api.deck.entity.Deck;
import com.logmein.deckcard.api.player.entity.Player;

import javax.persistence.*;
import java.util.List;

@Entity
public class Game {
    @Id
    @GeneratedValue
    private Long id;


    @OneToMany(mappedBy="game")
    private List<Deck> decks;

    @OneToMany(mappedBy="game")
    private List<Player> players;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Deck> getDecks() {
        return decks;
    }

    public void setDecks(List<Deck> decks) {
        this.decks = decks;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
