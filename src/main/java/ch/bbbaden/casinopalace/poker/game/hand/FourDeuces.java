package ch.bbbaden.casinopalace.poker.game.hand;

import ch.bbbaden.casinopalace.poker.game.Card;
import ch.bbbaden.casinopalace.poker.game.CardRank;
import ch.bbbaden.casinopalace.poker.game.CardSuit;

import java.util.Arrays;
import java.util.Optional;

public class FourDeuces implements HandParser {
    @Override
    public Optional<Hand> parse(Card... cards) {
        return new CardGroup().ofSameRank().add(new CardSelector().multiple(4)).find(cards).isPresent() ? Optional.ofNullable(getPossibleHand()) : Optional.empty();
        //return new HandChecker().wildcards(false).ofSameType(((CardOption[]) Arrays.stream(CardSuit.values()).map(x -> new CardOption().require(x)).toArray())).build(cards) ? Optional.ofNullable(getPossibleHand()) : Optional.empty();
    }

    @Override
    public Hand getPossibleHand() {
        return new Hand("Vier Deuces", 200);
    }
}
