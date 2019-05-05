package ch.bbbaden.casinopalace.poker.game.hand;

import ch.bbbaden.casinopalace.poker.game.Card;
import ch.bbbaden.casinopalace.poker.game.CardRank;
import ch.bbbaden.casinopalace.poker.game.CardSuit;
import org.junit.Test;

import static org.junit.Assert.*;

public class WildRoyalFlushTest {

    @Test
    public void parseOrdered() {
        assertTrue(new WildRoyalFlush().parse(new Card[]{
                new Card(CardRank.Ace, CardSuit.Hearts),
                new Card(CardRank.King, CardSuit.Hearts),
                new Card(CardRank.Queen, CardSuit.Hearts),
                new Card(CardRank.Jack, CardSuit.Hearts),
                new Card(CardRank.Two, CardSuit.Hearts)
        }).isPresent());
    }

    @Test
    public void parseUnordered() {
        assertTrue(new WildRoyalFlush().parse(new Card[]{
                new Card(CardRank.Two, CardSuit.Hearts),
                new Card(CardRank.Queen, CardSuit.Hearts),
                new Card(CardRank.Ace, CardSuit.Hearts),
                new Card(CardRank.Ten, CardSuit.Hearts),
                new Card(CardRank.Jack, CardSuit.Hearts)
        }).isPresent());
    }

    @Test
    public void parseIncorrectColorMatch(){
        assertFalse(new WildRoyalFlush().parse(new Card[]{
                new Card(CardRank.Ace, CardSuit.Hearts),
                new Card(CardRank.King, CardSuit.Hearts),
                new Card(CardRank.Queen, CardSuit.Hearts),
                new Card(CardRank.Jack, CardSuit.Hearts),
                new Card(CardRank.Two, CardSuit.Clubs)
        }).isPresent());
    }

    @Test
    public void parseIncorrectType(){
        assertFalse(new WildRoyalFlush().parse(new Card[]{
                new Card(CardRank.Ace, CardSuit.Hearts),
                new Card(CardRank.King, CardSuit.Hearts),
                new Card(CardRank.Queen, CardSuit.Hearts),
                new Card(CardRank.Nine, CardSuit.Hearts),
                new Card(CardRank.Two, CardSuit.Hearts)
        }).isPresent());
    }
}