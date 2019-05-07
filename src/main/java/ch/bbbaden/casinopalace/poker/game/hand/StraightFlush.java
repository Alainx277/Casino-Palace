package ch.bbbaden.casinopalace.poker.game.hand;

import ch.bbbaden.casinopalace.poker.game.Card;
import ch.bbbaden.casinopalace.poker.game.CardRank;
import ch.bbbaden.casinopalace.poker.game.CardSuit;

import java.util.Optional;

public class StraightFlush implements HandParser {
    @Override
    public Optional<Hand> parse(Card... cards) {
        return new CardGroup()
                .sequential()
                .ofSameSuit()
                .add(new CardSelector()
                        .ofRank(CardRank.Ace)
                        .not()
                        .multiple(5)
                ).find(cards)

                .isPresent() ? Optional.ofNullable(getPossibleHand()) : Optional.empty();
    }

    @Override
    public Hand getPossibleHand() {
        return new Hand("Straight Flush", 9);
    }
}
