package com.logmein.deckcard.api.player.service;

import com.logmein.deckcard.api.game.entity.Game;
import com.logmein.deckcard.api.player.entity.Player;

import java.util.List;

public interface PlayerService {
    Player addPlayerToGame(Game game, Player player);

    List<Player> findPlayerByGame(Game game);

    Player findPlayerById(Long id);
}
