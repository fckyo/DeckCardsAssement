package com.logmein.deckcard.api.game.controller;

import com.logmein.deckcard.api.deck.entity.Deck;
import com.logmein.deckcard.api.deck.service.DeckService;
import com.logmein.deckcard.api.game.entity.Game;
import com.logmein.deckcard.api.game.exception.GameNotFoundException;
import com.logmein.deckcard.api.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {

    private GameService gameService;
    private DeckService deckService;

    @Autowired
    public GameController(GameService gameService, DeckService deckService){
        this.gameService = gameService;
        this.deckService = deckService;
    }

    @GetMapping("/deckcards/games")
    List<Game> allGames() {
        return gameService.findAllGames();
    }

    @GetMapping("/deckcards/games/{id}")
    Game findGame(@PathVariable Long id) {
        return gameService.findGameById(id);
    }

    @PostMapping("/deckcards/games")
    Game newGame() {
        return gameService.createNewGame();
    }

    @DeleteMapping("/deckcards/games/{id}")
    Game deleteGame(@PathVariable Long id){
        Game game = gameService.deleteGame(id);
        if (game == null)
            throw new GameNotFoundException(id);
        return game;
    }

    @PutMapping("/deckcards/games/{idGame}/deck/{idDeck}/deal/{noOfCards}")
    Game dealCardsToPlayers(@PathVariable Long idGame, @PathVariable Long idDeck, @PathVariable Integer noOfCards){
        Game game = gameService.findGameById(idGame);
        if (game == null)
            throw new GameNotFoundException(idGame);

        Deck deck = deckService.findDeckById(idDeck);

        return gameService.dealCardsToPLayers(game, deck, noOfCards);
    }

}
