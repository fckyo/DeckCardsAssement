package com.logmein.deckcard.api.game.service;

import com.logmein.deckcard.api.card.dao.DeckCardDAO;
import com.logmein.deckcard.api.game.dao.GameDAO;
import com.logmein.deckcard.api.game.entity.Game;
import com.logmein.deckcard.api.game.service.impl.DefaultGameService;
import com.logmein.deckcard.api.player.dao.PlayerDAO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GameService_UT {
    private GameDAO gameDAO;
    private PlayerDAO playerDAO;
    private DeckCardDAO deckCardDAO;
    private GameService service;

    @Before
    public void setUp(){
        gameDAO = mock(GameDAO.class);

        service = new DefaultGameService(gameDAO, playerDAO, deckCardDAO);
    }

    @Test
    public void testCreateNewGame(){
        when(gameDAO.save(any(Game.class))).thenReturn(new Game());

        Game game = service.createNewGame();

        verify(gameDAO, times(1)).save(any(Game.class));

        Assert.assertNotNull(game);
    }

    @Test
    public void testDeleteGameWithNoExistingGame(){
        when(gameDAO.findById(anyLong())).thenReturn(Optional.empty());

        Game game = service.deleteGame(1L);

        Assert.assertNull(game);
    }

    @Test
    public void testDeleteGame(){
        when(gameDAO.findById(anyLong())).thenReturn(Optional.of(new Game()));

        service.deleteGame(1L);

        verify(gameDAO, times(1)).delete(any(Game.class));
    }

    @Test
    public void testFindAllGamesInSystem(){
        when(gameDAO.findAll()).thenReturn(Arrays.asList(new Game()));

        List<Game> games = service.findAllGames();

        verify(gameDAO,times(1)).findAll();

        Assert.assertNotNull(games);

        Assert.assertFalse(games.isEmpty());

    }

    @Test
    public void testFindGameById(){
        when(gameDAO.findById(anyLong())).thenReturn(Optional.of(new Game()));

        Game game = service.findGameById(1L);

        verify(gameDAO, times(1)).findById(anyLong());

        Assert.assertNotNull(game);
    }

}
