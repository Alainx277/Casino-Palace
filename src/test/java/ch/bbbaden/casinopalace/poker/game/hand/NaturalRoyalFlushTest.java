package ch.bbbaden.casinopalace.poker.game.hand;

import ch.bbbaden.casinopalace.poker.game.Card;
import ch.bbbaden.casinopalace.poker.game.CardSuit;
import ch.bbbaden.casinopalace.poker.game.CardRank;
import org.junit.Test;

import static org.junit.Assert.*;

public class NaturalRoyalFlushTest {

    @Test
    public void parseOrdered() {
        assertTrue(new NaturalRoyalFlush().parse(new Card[]{
                new Card(CardRank.Ace, CardSuit.Hearts),
                new Card(CardRank.King, CardSuit.Hearts),
                new Card(CardRank.Queen, CardSuit.Hearts),
                new Card(CardRank.Jack, CardSuit.Hearts),
                new Card(CardRank.Ten, CardSuit.Hearts)
        }).isPresent());
    }

    @Test
    public void parseUnordered() {
        assertTrue(new NaturalRoyalFlush().parse(new Card[]{
                new Card(CardRank.King, CardSuit.Hearts),
                new Card(CardRank.Queen, CardSuit.Hearts),
                new Card(CardRank.Ace, CardSuit.Hearts),
                new Card(CardRank.Ten, CardSuit.Hearts),
                new Card(CardRank.Jack, CardSuit.Hearts)
        }).isPresent());
    }

    @Test
    public void parseIncorrectColorMatch(){
        assertFalse(new NaturalRoyalFlush().parse(new Card[]{
                new Card(CardRank.Ace, CardSuit.Hearts),
                new Card(CardRank.King, CardSuit.Hearts),
                new Card(CardRank.Queen, CardSuit.Hearts),
                new Card(CardRank.Jack, CardSuit.Hearts),
                new Card(CardRank.Ten, CardSuit.Clubs)
        }).isPresent());
    }

    @Test
    public void parseIncorrectType(){
        assertFalse(new NaturalRoyalFlush().parse(new Card[]{
                new Card(CardRank.Ace, CardSuit.Hearts),
                new Card(CardRank.King, CardSuit.Hearts),
                new Card(CardRank.Queen, CardSuit.Hearts),
                new Card(CardRank.Jack, CardSuit.Hearts),
                new Card(CardRank.Nine, CardSuit.Hearts)
        }).isPresent());
    }
}