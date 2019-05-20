package com.logmein.deckcard.api.deck.dto;

import com.logmein.deckcard.api.card.entity.Card;

public class SuitCardsGroup {
    private String suit;
    private Long quantity;

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
