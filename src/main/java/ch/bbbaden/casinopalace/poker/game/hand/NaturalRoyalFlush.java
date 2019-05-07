package ch.bbbaden.casinopalace.poker.game.hand;

import ch.bbbaden.casinopalace.poker.game.Card;
import ch.bbbaden.casinopalace.poker.game.CardRank;

import java.util.Optional;

public class NaturalRoyalFlush implements HandParser {
    @Override
    public Optional<Hand> parse(Card... cards) {
        return new CardGroup().ofSameSuit().add(
                new CardSelector().ofRank(CardRank.Ace),
                new CardSelector().ofRank(CardRank.King),
                new CardSelector().ofRank(CardRank.Queen),
                new CardSelector().ofRank(CardRank.Jack),
                new CardSelector().ofRank(CardRank.Ten)
        ).find(cards).isPresent() ? Optional.ofNullable(getPossibleHand()) : Optional.empty();
    }

    @Override
    public Hand getPossibleHand() {
        return new Hand("Natural Royal Flush", 800);
    }
}
