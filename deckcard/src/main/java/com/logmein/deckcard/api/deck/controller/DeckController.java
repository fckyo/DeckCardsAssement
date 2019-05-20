package com.logmein.deckcard.api.deck.controller;

import com.logmein.deckcard.api.card.entity.Card;
import com.logmein.deckcard.api.deck.dto.SuitCardsGroup;
import com.logmein.deckcard.api.deck.entity.Deck;
import com.logmein.deckcard.api.deck.service.DeckService;
import com.logmein.deckcard.api.game.entity.Game;
import com.logmein.deckcard.api.game.exception.GameNotFoundException;
import com.logmein.deckcard.api.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@RestController
public class DeckController {
    private DeckService deckService;
    private GameService gameService;

    @Autowired
    public DeckController(DeckService deckService, GameService gameService){
        this.deckService = deckService;
        this.gameService = gameService;
    }
    @PostMapping("/deckcards/games/{id}/deck")
    Deck addDeckToGame(@PathVariable Long id){
        Game game = gameService.findGameById(id);
        if (game == null)
            throw new GameNotFoundException(id);

        return deckService.createDeckToGame(game);
    }

    @GetMapping("/deckcards/games/{id}/deck")
    List<Deck> findAllDeckByGame(@PathVariable Long id){
        Game game = gameService.findGameById(id);
        if (game == null)
            throw new GameNotFoundException(id);

        return deckService.findDecksByGame(game);
    }

    @PutMapping("/deckcards/games/deck/{id}/shuffle")
    void shuffled(@PathVariable Long id){
        Deck deck = deckService.findDeckById(id);
        deckService.shuffle(deck);
    }

    @GetMapping("/deckcards/games/deck/{id}/count")
    List<SuitCardsGroup> getGroupedCardsBySuit(@PathVariable Long id){
        Deck deck = deckService.findDeckById(id);
        Map<String, List<Card>> map = deck.getDeckCards().stream()
                                                         .filter(deckCard -> !deckCard.getAssigned().booleanValue())
                                                         .map(deckCard -> deckCard.getCard())
                                                         .collect(groupingBy(Card::getSuit));
        final List<SuitCardsGroup> groups = new ArrayList<SuitCardsGroup>();
        map.forEach((s, cards) -> {
            SuitCardsGroup group = new SuitCardsGroup();
            group.setQuantity(new Long(cards.size()));
            group.setSuit(s);
            groups.add(group);
        });
        return groups;
    }
}
