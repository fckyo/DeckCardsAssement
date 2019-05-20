package com.logmein.deckcard.api.deck.service.impl;

import com.logmein.deckcard.api.card.dao.CardDAO;
import com.logmein.deckcard.api.card.dao.DeckCardDAO;
import com.logmein.deckcard.api.card.entity.Card;
import com.logmein.deckcard.api.deck.dao.DeckDAO;
import com.logmein.deckcard.api.deck.entity.Deck;
import com.logmein.deckcard.api.deck.entity.DeckCard;
import com.logmein.deckcard.api.deck.service.DeckService;
import com.logmein.deckcard.api.game.entity.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DefaultDeckService implements DeckService {
    private CardDAO cardDAO;
    private DeckDAO deckDAO;
    private DeckCardDAO deckCardDAO;

    @Autowired
    public DefaultDeckService(CardDAO cardDAO, DeckDAO deckDAO, DeckCardDAO deckCardDAO){
        this.deckDAO = deckDAO;
        this.cardDAO = cardDAO;
        this.deckCardDAO = deckCardDAO;
    }

    @Override
    public Deck createDeckToGame(Game game) {
        Deck deck = new Deck();
        deck.setName("New Deck");
        deck.setGame(game);
        deck = deckDAO.save(deck);
        List<Card> cards = cardDAO.findAll();

        /*
            Creating a new Deck with shuffled cards!!!
        */
        List<Integer> assignedCardsIndexes = getRandomCardsIndexes(cards.size(), cards.size());

        deck.setDeckCards(new ArrayList<DeckCard>());
        DeckCard deckCard = null;
        for(Integer index : assignedCardsIndexes){
            deckCard = new DeckCard();
            deckCard.setDeck(deck);
            deckCard.setCard(cards.get(index));
            deck.getDeckCards().add(deckCardDAO.save(deckCard));
        }

        return deck;
    }

    public void shuffle(Deck deck) {
        List<DeckCard> cards = deckCardDAO.findByDeckAndAssignedFalse(deck);

        /*
            Creating a new Deck with shuffled cards!!!
        */
        deckCardDAO.deleteAll(cards);

        List<Integer> assignedCardsIndexes = getRandomCardsIndexes(cards.size(), cards.size());
        deck.setDeckCards(new ArrayList<DeckCard>());
        DeckCard deckCard = null;
        for(Integer index : assignedCardsIndexes){
            deckCard = new DeckCard();
            deckCard.setDeck(deck);
            deckCard.setCard(cards.get(index).getCard());
            deck.getDeckCards().add(deckCardDAO.save(deckCard));
        }
    }

    @Override
    public List<Deck> findDecksByGame(Game game) {
        return deckDAO.findByGame(game);
    }

    @Override
    public Deck findDeckById(Long idDeck) {
        return deckDAO.findById(idDeck).orElseGet(() -> {
            return null;
        });
    }

    private List<Integer> getRandomCardsIndexes(int n, int listSize) {
        List<Integer> indexes = new ArrayList<Integer>();
        List<Integer> randomIndexes = new ArrayList<Integer>();

        for(int i=0; i < listSize; i++){
            indexes.add(i);
        }

        Collections.shuffle(indexes);

        for(int i=0; i < n; i++)
            randomIndexes.add(indexes.get(i));
        return randomIndexes;
    }

}
