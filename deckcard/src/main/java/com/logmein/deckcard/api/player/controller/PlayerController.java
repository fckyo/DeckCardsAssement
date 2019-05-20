package com.logmein.deckcard.api.player.controller;

import com.logmein.deckcard.api.card.entity.Card;
import com.logmein.deckcard.api.game.entity.Game;
import com.logmein.deckcard.api.game.exception.GameNotFoundException;
import com.logmein.deckcard.api.game.service.GameService;
import com.logmein.deckcard.api.player.entity.Player;
import com.logmein.deckcard.api.player.exception.PlayerNotFoundException;
import com.logmein.deckcard.api.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
public class PlayerController {
    private PlayerService playerService;
    private GameService gameService;

    @Autowired
    public PlayerController(PlayerService playerService, GameService gameService){
        this.playerService = playerService;
        this.gameService = gameService;
    }

    @PostMapping("/deckcards/games/{id}/player")
    Player addPlayerToGame(@RequestBody Player player, @PathVariable Long id){
        Game game = gameService.findGameById(id);
        if (game == null)
            throw new GameNotFoundException(id);

        return playerService.addPlayerToGame(game, player);
    }

    @GetMapping("/deckcards/games/{id}/player")
    List<Player> findAllPlayerByGame(@PathVariable Long id){
        Game game = gameService.findGameById(id);
        if (game == null)
            throw new GameNotFoundException(id);

        return playerService.findPlayerByGame(game);
    }

    @GetMapping("/deckcards/games/player/{idPlayer}/cards")
    List<Card> getCardsByPlayer(@PathVariable Long idPlayer){
        Player player = playerService.findPlayerById(idPlayer);

        if (player == null)
            throw new PlayerNotFoundException(idPlayer);

        return player.getCardsPerPlayers().stream().map(deckCard -> deckCard.getCard()).collect(Collectors.toList());
    }

    @GetMapping("/deckcards/games/{id}/player/sorted")
    List<Player> getSortedPlayersByGame(@PathVariable Long id){
        Game game = gameService.findGameById(id);
        if (game == null)
            throw new GameNotFoundException(id);

        return playerService.findPlayerByGame(game).stream().sorted(Comparator.comparing(Player::getTotalCardsValue).reversed()).collect(Collectors.toList());
    }

}
