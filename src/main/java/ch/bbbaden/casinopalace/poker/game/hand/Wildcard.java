package ch.bbbaden.casinopalace.poker.game.hand;

import ch.bbbaden.casinopalace.poker.game.CardRank;

public class Wildcard {
    public static CardSelector or(CardSelector selector){
        return new CardSelector().ofRank(CardRank.Two).or(selector);
    }

    public static boolean is(CardRank rank){
        return rank == CardRank.Two;
    }
}
