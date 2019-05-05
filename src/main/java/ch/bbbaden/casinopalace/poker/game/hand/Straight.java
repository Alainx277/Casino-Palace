package ch.bbbaden.casinopalace.poker.game.hand;

import ch.bbbaden.casinopalace.poker.game.Card;
import ch.bbbaden.casinopalace.poker.game.CardRank;

import java.util.Optional;

public class Straight implements HandParser {
    @Override
    public Optional<Hand> parse(Card... cards) {
        return new CardGroup()
                .sequential()
                .wildcards()
                .add(new CardSelector()
                        .multiple(5)
                )
                .or(new CardGroup()
                        .sequential()
                        .add(new CardSelector()
                                .ofRank(CardRank.Two)
                                , new CardSelector()
                                , new CardSelector()
                                , new CardSelector()
                        )
                        .and(new CardGroup()
                                .add(new CardSelector()
                                        .ofRank(CardRank.Ace)
                                )
                        )
                )
                .find(cards).isPresent() ? Optional.ofNullable(getPossibleHand()) : Optional.empty();
    }

    @Override
    public Hand getPossibleHand() {
        return new Hand("Straight", 2);
    }
}
