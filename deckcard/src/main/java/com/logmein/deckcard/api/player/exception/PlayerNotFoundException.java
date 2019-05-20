package com.logmein.deckcard.api.player.exception;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(Long id) {
        super("Player with ID " + id + " not found.");
    }
}
