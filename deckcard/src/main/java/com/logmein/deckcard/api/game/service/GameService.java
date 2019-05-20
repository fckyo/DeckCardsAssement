package com.logmein.deckcard.api.game.service;

import com.logmein.deckcard.api.deck.entity.Deck;
import com.logmein.deckcard.api.game.entity.Game;
import com.logmein.deckcard.api.player.entity.Player;

import java.util.List;

public interface GameService {
    Game createNewGame();

    Game deleteGame(Long idGame);

    List<Game> findAllGames();

    Game findGameById(Long id);

    Game dealCardsToPLayers(Game game, Deck deck, int noOfCards);
}
