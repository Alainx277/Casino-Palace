package ch.bbbaden.casinopalace.poker.game.hand;

import ch.bbbaden.casinopalace.poker.game.Card;
import ch.bbbaden.casinopalace.poker.game.CardRank;

import java.util.Optional;

public class WildRoyalFlush implements HandParser {
    @Override
    public Optional<Hand> parse(Card... cards) {
        return new CardGroup().ofSameSuit().add(
                Wildcard.or(new CardSelector().ofRank(CardRank.Ace)),
                Wildcard.or(new CardSelector().ofRank(CardRank.King)),
                Wildcard.or(new CardSelector().ofRank(CardRank.Queen)),
                Wildcard.or(new CardSelector().ofRank(CardRank.Jack)),
                Wildcard.or(new CardSelector().ofRank(CardRank.Ten))
        ).find(cards).isPresent() ? Optional.ofNullable(getPossibleHand()) : Optional.empty();
    }

    @Override
    public Hand getPossibleHand() {
        return new Hand("Wild Royal Flush", 25);
    }
}
