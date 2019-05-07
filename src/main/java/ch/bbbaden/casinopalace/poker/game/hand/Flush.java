package ch.bbbaden.casinopalace.poker.game.hand;

import ch.bbbaden.casinopalace.poker.game.Card;
import ch.bbbaden.casinopalace.poker.game.CardRank;

import java.util.Optional;

public class Flush implements HandParser {
    @Override
    public Optional<Hand> parse(Card... cards) {
        return new CardGroup()
                .ofSameSuit()
                .add(new CardSelector()
                        .multiple(5)
                )
                .find(cards).isPresent() ? Optional.ofNullable(getPossibleHand()) : Optional.empty();
    }

    @Override
    public Hand getPossibleHand() {
        return new Hand("Flush", 2);
    }
}
