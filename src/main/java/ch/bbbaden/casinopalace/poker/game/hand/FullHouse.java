package ch.bbbaden.casinopalace.poker.game.hand;

import ch.bbbaden.casinopalace.poker.game.Card;

import java.util.Optional;

public class FullHouse implements HandParser {
    @Override
    public Optional<Hand> parse(Card... cards) {
        return new CardGroup()
                .wildcards()
                .ofSameRank()
                .add(new CardSelector()
                        .multiple(3)
                )
                .and(new CardGroup()
                        .wildcards()
                        .ofSameRank()
                        .add(new CardSelector()
                                .multiple(2)))
                .find(cards).isPresent() ? Optional.of(getPossibleHand()) : Optional.empty();
    }

    @Override
    public Hand getPossibleHand() {
        return new Hand("Full House", 3);
    }
}
