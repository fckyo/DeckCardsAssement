package com.logmein.deckcard.api.player.service;

import com.logmein.deckcard.api.game.entity.Game;
import com.logmein.deckcard.api.player.dao.PlayerDAO;
import com.logmein.deckcard.api.player.entity.Player;
import com.logmein.deckcard.api.player.service.impl.DefaultPlayerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class PlayerService_UT {
    private PlayerDAO dao;
    private PlayerService service;

    @Before
    public void setUp(){
        dao = mock(PlayerDAO.class);
        service = new DefaultPlayerService(dao);

    }

    @Test
    public void addPlayersToGame(){
        Game game = new Game();
        game.setId(1L);

        Player player = new Player();
        player.setGame(game);
        player.setName("TEST");
    }
}
