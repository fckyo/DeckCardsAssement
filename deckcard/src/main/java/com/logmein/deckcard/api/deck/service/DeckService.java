package com.logmein.deckcard.api.deck.service;

import com.logmein.deckcard.api.deck.entity.Deck;
import com.logmein.deckcard.api.game.entity.Game;

import java.util.List;

public interface DeckService {
    Deck createDeckToGame(Game game);

    List<Deck> findDecksByGame(Game game);

    Deck findDeckById(Long idDeck);

    void shuffle(Deck deck);
}
