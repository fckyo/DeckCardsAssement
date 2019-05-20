package com.logmein.deckcard.api.card.dao;

import com.logmein.deckcard.api.card.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardDAO extends JpaRepository<Card, Long> {
}
