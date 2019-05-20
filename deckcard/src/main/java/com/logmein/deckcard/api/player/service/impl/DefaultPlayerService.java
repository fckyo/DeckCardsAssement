package com.logmein.deckcard.api.player.service.impl;

import com.logmein.deckcard.api.game.entity.Game;
import com.logmein.deckcard.api.player.dao.PlayerDAO;
import com.logmein.deckcard.api.player.entity.Player;
import com.logmein.deckcard.api.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultPlayerService implements PlayerService {
    private PlayerDAO dao;

    @Autowired
    public DefaultPlayerService(PlayerDAO dao) {
        this.dao = dao;
    }

    @Override
    public Player addPlayerToGame(Game game, Player player) {
        player.setGame(game);
        return dao.save(player);

    }

    @Override
    public List<Player> findPlayerByGame(Game game) {
        return dao.findByGame(game);
    }

    @Override
    public Player findPlayerById(Long id) {
        return dao.findById(id).orElseGet(() -> {return null;});
    }
}
