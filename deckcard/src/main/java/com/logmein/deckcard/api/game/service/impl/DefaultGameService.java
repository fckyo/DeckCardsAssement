package com.logmein.deckcard.api.game.service.impl;

import com.logmein.deckcard.api.card.dao.DeckCardDAO;
import com.logmein.deckcard.api.deck.dao.DeckDAO;
import com.logmein.deckcard.api.deck.entity.Deck;
import com.logmein.deckcard.api.deck.entity.DeckCard;
import com.logmein.deckcard.api.game.dao.GameDAO;
import com.logmein.deckcard.api.game.entity.Game;
import com.logmein.deckcard.api.game.service.GameService;
import com.logmein.deckcard.api.player.dao.PlayerDAO;
import com.logmein.deckcard.api.player.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class DefaultGameService implements GameService {
    private final GameDAO gameDAO;
    private final PlayerDAO playerDAO;
    private final DeckCardDAO deckCardDAO;

    @Autowired
    public DefaultGameService(GameDAO gameDAO, PlayerDAO playerDAO, DeckCardDAO deckCardDAO) {
        this.gameDAO = gameDAO;
        this.playerDAO = playerDAO;
        this.deckCardDAO = deckCardDAO;
    }


    @Override
    public Game createNewGame() {
        return gameDAO.save(new Game());
    }

    @Override
    public Game deleteGame(Long idGame) {
        return gameDAO.findById(idGame).map(game -> {
            gameDAO.delete(game);
            return game;
        }).orElseGet(() -> {
            return null;
        });

    }

    @Override
    public List<Game> findAllGames() {
        return gameDAO.findAll();
    }

    @Override
    public Game findGameById(Long id) {
        return gameDAO.findById(id).orElseGet(() -> {
            return null;
        });
    }

    @Override
    public Game dealCardsToPLayers(Game game, Deck deck, int noOfCards) {

        List<DeckCard> cards = deck.getDeckCards().stream().filter(deckCard -> !deckCard.getAssigned().booleanValue()).collect(Collectors.toList());

        if(cards == null || cards.isEmpty())
            return game;

        for(Player player : game.getPlayers()){
            List<DeckCard> cardsPerPlayer = IntStream.range(0,noOfCards)
                                                           .mapToObj(i -> {
                                                               DeckCard deckCard = cards.remove(i);
                                                               deckCard.setAssigned(Boolean.TRUE);
                                                               return deckCardDAO.save(deckCard);
                                                           })
                                                           .collect(Collectors.toList());
            if(player.getCardsPerPlayers() == null || player.getCardsPerPlayers().isEmpty())
                player.setCardsPerPlayers(cardsPerPlayer);
            else
                player.getCardsPerPlayers().addAll(cardsPerPlayer);

            playerDAO.save(player);

        }

        return gameDAO.getOne(game.getId());
    }

}
