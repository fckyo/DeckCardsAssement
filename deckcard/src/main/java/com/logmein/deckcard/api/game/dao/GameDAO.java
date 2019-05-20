package com.logmein.deckcard.api.game.dao;

import com.logmein.deckcard.api.game.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameDAO extends JpaRepository<Game, Long> {
}
