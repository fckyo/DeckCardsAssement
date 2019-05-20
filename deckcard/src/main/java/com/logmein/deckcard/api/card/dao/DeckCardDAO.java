package com.logmein.deckcard.api.card.dao;

import com.logmein.deckcard.api.deck.entity.Deck;
import com.logmein.deckcard.api.deck.entity.DeckCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeckCardDAO extends JpaRepository<DeckCard, Long> {
    List<DeckCard> findByDeckAndAssignedFalse(Deck deck);
}
