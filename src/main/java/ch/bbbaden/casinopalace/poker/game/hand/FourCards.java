package ch.bbbaden.casinopalace.poker.game.hand;

import ch.bbbaden.casinopalace.poker.game.Card;

import java.util.Optional;

public class FourCards implements HandParser {
    @Override
    public Optional<Hand> parse(Card... cards) {
        return new CardGroup()
                .wildcards()
                .ofSameRank()
                .add(new CardSelector()
                        .multiple(4)
                )
                .find(cards).isPresent() ? Optional.of(getPossibleHand()) : Optional.empty();
    }

    @Override
    public Hand getPossibleHand() {
        return new Hand("Vierling", 5);
    }
}
