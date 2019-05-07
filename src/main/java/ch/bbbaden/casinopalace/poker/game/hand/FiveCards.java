package ch.bbbaden.casinopalace.poker.game.hand;

import ch.bbbaden.casinopalace.poker.game.Card;

import java.util.Optional;

public class FiveCards implements HandParser {
    @Override
    public Optional<Hand> parse(Card... cards) {
        return new CardGroup().ofSameRank().wildcards().add(new CardSelector().multiple(5)).find(cards).isPresent() ? Optional.ofNullable(getPossibleHand()) : Optional.empty();
    }

    @Override
    public Hand getPossibleHand() {
        return new Hand("Fünf gleiche Karten", 15);
    }
}
