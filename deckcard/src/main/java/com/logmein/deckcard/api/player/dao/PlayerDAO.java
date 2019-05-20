package com.logmein.deckcard.api.player.dao;

import com.logmein.deckcard.api.game.entity.Game;
import com.logmein.deckcard.api.player.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerDAO extends JpaRepository<Player, Long> {
    List<Player> findByGame(Game game);
}
