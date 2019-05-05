package ch.bbbaden.casinopalace.poker.game.hand;

import ch.bbbaden.casinopalace.poker.game.Card;

import java.util.Optional;

public interface HandParser {
    Optional<Hand> parse(Card... cards);
    Hand getPossibleHand();
}
