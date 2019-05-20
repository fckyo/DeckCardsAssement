package com.logmein.deckcard.api.deck.dao;

import com.logmein.deckcard.api.deck.entity.Deck;
import com.logmein.deckcard.api.game.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeckDAO extends JpaRepository<Deck, Long> {
    List<Deck> findByGame(Game game);
}
